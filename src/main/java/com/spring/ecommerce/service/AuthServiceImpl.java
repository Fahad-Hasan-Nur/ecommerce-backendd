package com.spring.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.email.EmailService;
import com.spring.ecommerce.email.MailRequest;
import com.spring.ecommerce.emailConfirmation.ConfirmationToken;
import com.spring.ecommerce.emailConfirmation.ConfirmationTokenRepo;
import com.spring.ecommerce.emailConfirmation.EmailConfig;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.ImageRepo;
import com.spring.ecommerce.repository.UserRepo;
import com.spring.ecommerce.security.jwt.AuthenticationRequest;
import com.spring.ecommerce.security.jwt.AuthenticationResponse;
import com.spring.ecommerce.security.jwt.JwtProvider;
import com.spring.ecommerce.security.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link AuthServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
	private  UserDetailsServiceImpl service;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ConfirmationTokenRepo tokenRepo;
	@Autowired
	EmailConfig emaiConfig;
	@Autowired
	ImageRepo imageRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;


	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @return {@link String}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> create(User user) {
		User existingUser = userRepo.findByEmailIgnoreCase(user.getEmail());
		User existingUserPhone = userRepo.findByphoneNumberIgnoreCase(user.getPhoneNumber());

		if (existingUser != null) {
			log.warn("Failed to create  User: ");
			 return new ResponseEntity<>(
				      "Email already registered...!!", 
				      HttpStatus.OK);
		}else if(existingUserPhone!=null) {
			log.warn("Failed to create  User: ");
			 return new ResponseEntity<>(
				      "Phone number already registered...!!", 
				      HttpStatus.OK);
		}
		else {
			try {
				user.setType("user");
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				if(user.getImageId()!=null) {
					user.setImage(imageRepo.findById(user.getImageId()).orElse(null));
				}
				if(user.getRole().equals("USER")) {
					user.setVerified(true);
				}
				ConfirmationToken confirmationToken = new ConfirmationToken(user);
				userRepo.save(user);
				tokenRepo.save(confirmationToken);

				if(user.getRole().equals("USER")) {
					user.setVerified(true);
					ConfirmationToken token=tokenRepo.findByUser(user);
					MailRequest ob=new MailRequest();
					ob.setFrom("nurecommercesite@gmail.com");
					ob.setTo(user.getEmail());
					ob.setName("Admin");
					ob.setSubject("Confirmation Email");
					Map<String, Object> model = new HashMap<>();
					model.put("Name", user.getName());
					model.put("token", token.getConfirmationToken());
					emailService.sendEmail(ob, model,"email-user-activate.ftl");
					log.warn("Email Send " );

				}
			} catch (Exception e) {
				log.warn("Failed to create  User: ", e);
				return ResponseEntity.ok("Failed to create  User ");

			}
		}
		return ResponseEntity.ok(user);

	}

	/*************************************************************************
	 * User Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> createTokenForUser(AuthenticationRequest authenticationRequest) throws Exception {

		final User user = userRepo.findByEmail(authenticationRequest.getEmail());
		if (user == null) {
			return ResponseEntity.ok("No user found With given email..!!");
		}else if(userRepo.findByEmailAndActive(authenticationRequest.getEmail(), true)==null){
			return ResponseEntity.ok("Account is not activated Yet...Chack mailbox for verification email.");
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.ok("Incorrect Password..!!!");
		}
		final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());

		final String jwt = jwtProvider.generateToken(userDetails);

		System.out.println(jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	/*************************************************************************
	 * Activate newly created USer
	 * 
	 * @param ob {@link Token} String
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link String}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> confirmUserAccount(String token) {
		ConfirmationToken cToken = tokenRepo.findByConfirmationToken(token);
		if (cToken != null) {
			User user = userRepo.findByEmailIgnoreCase(cToken.getUser().getEmail());
			user.setActive(true);
			userRepo.save(user);
			tokenRepo.delete(cToken);
			return ResponseEntity.ok(new User());
		} else {
			return ResponseEntity.ok("Error");
		}
	}
	
	/*************************************************************************
	 * User Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> createTokenForAdmin(AuthenticationRequest authenticationRequest) throws Exception {
		List<User> user=userRepo.findAll();
		if(user.isEmpty()) {
			initializeAdmin();
		}
		final User admin = userRepo.findByEmailAndActiveAndVerified(authenticationRequest.getEmail(), true,true);
		if (admin == null) {
			return ResponseEntity.ok("Admin  don't Exist...");
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.ok("Incorrect Password..!!!");
			
		}
		final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());

		final String jwt = jwtProvider.generateToken(userDetails);

		System.out.println(jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	/*************************************************************************
	 * Initialize First super admin
	 * 
	 * @param ob {@link User} object
	 * @throws Exception 
	 *************************************************************************/
	public ResponseEntity<?> initializeAdmin() throws Exception {
		User ob=new User();
		ob.setEmail("superadmin@gmail.com");
		ob.setPassword(passwordEncoder.encode("superadmin"));
		ob.setName("Super Admin");
		ob.setPhoneNumber("12345");
		ob.setRole("SUPER_ADMIN");
		ob.setActive(true);
		ob.setVerified(true);
		ob.setType("admin");
		try {
			userRepo.save(ob);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Email Or Pasword.", e);
		}
		return null;
	}
}
