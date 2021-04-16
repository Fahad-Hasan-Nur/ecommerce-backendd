package com.spring.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.email.EmailService;
import com.spring.ecommerce.email.MailRequest;
import com.spring.ecommerce.emailConfirmation.ConfirmationToken;
import com.spring.ecommerce.emailConfirmation.ConfirmationTokenRepo;
import com.spring.ecommerce.emailConfirmation.EmailConfig;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.VerifiedDealerInfo;
import com.spring.ecommerce.repository.ImageRepo;
import com.spring.ecommerce.repository.UserRepo;
import com.spring.ecommerce.repository.VerifiedDealerInfoRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link ProductServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-02-13
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdminServiceImpl implements AdminService {

	private final ImageRepo imageRepo;
	private final UserRepo adminRepo;
	private final VerifiedDealerInfoRepo vRepo;
	private final EmailConfig emailService;
	private final EmailService service;
	private final ConfirmationTokenRepo tokenRepo;

	/*************************************************************************
	 * Create a new Admin
	 * 
	 * @param ob {@link Admin} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Admin}
	 *************************************************************************/
	@Override
	public User create(User admin) {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			admin.setType("admin");
			admin.setActive(true);
			admin.setVerified(true);
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			admin.setImage(imageRepo.findById(admin.getImageId()).orElse(null));
			return adminRepo.save(admin);
		} catch (Exception e) {
			log.warn("Failed to create  Admin: ", e);
			return admin;
		}
	}

	/*************************************************************************
	 * Get all Admin {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@Override
	public List<UserDto> getAllAdmin() {
		return adminRepo.findAllByType("admin").stream().map(this::getUserDtoFromEntity).collect(Collectors.toList());
	}
	
	/*************************************************************************
	 * Get all Active Dealers {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@Override
	public List<UserDto> getAllActiveDealers() {
		return adminRepo.findAllByRoleAndActiveAndVerified("DEALER",true,true).stream().map(this::getUserDtoFromEntity).collect(Collectors.toList());
	}
	/*************************************************************************
	 * Get all Admin {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@Override
	public List<UserDto> getAllInactiveDealers() {
		return adminRepo.findAllByRoleAndActiveAndVerified("DEALER",false,false).stream().map(this::getUserDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get Admin {@link Admin} by Id
	 * 
	 * @return {@link Admin}
	 *************************************************************************/
	@Override
	public UserDto getUserDtoById(String id) {
		return adminRepo.findById(id).map(this::getUserDtoFromEntity).orElse(null);
	}

	/*************************************************************************
	 * Get Admin {@link Admin} by Email
	 * 
	 * @return {@link Admin}
	 *************************************************************************/
	@Override
	public UserDto getUserDtoByEmail(String email) {
		return this.getUserDtoFromEntity(adminRepo.findByEmail(email));
	}

	/*************************************************************************
	 * Update {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	@Override
	public User update(User ob) {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			User existingUser = adminRepo.findById(ob.getId()).orElse(null);
			if(ob.getPassword()!=null) {
				ob.setPassword(passwordEncoder.encode(ob.getPassword()));
			}else {
				ob.setPassword(existingUser.getPassword());
			}
			ob.setImage(imageRepo.findById(ob.getImageId()).orElse(null));
			BeanUtils.copyProperties(ob, existingUser);
			return adminRepo.save(existingUser);
		} catch (Exception e) {
			log.warn("Failed to update  Product: ", e);
			return ob;
		}
	}

	/*************************************************************************
	 * Delete {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> deleteById(String id) {
		try {
			adminRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to update  Product: ", e);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.BAD_REQUEST);
		}
	}

	/*************************************************************************
	 * Verify Dealer {@link Admin}
	 * 
	 * @return {@link Admin}
	 *************************************************************************/

	public User verifyDealer( String id) {
		User ob=adminRepo.findById(id).orElse(null);
		ob.setVerified(true);
		adminRepo.save(ob);
		this.sendConfirmation(ob);
		return ob;
	}
	
	/*************************************************************************
	 * Send email to Dealer
	 * 
	 * @return  email
	 *************************************************************************/
	private void sendConfirmation(User user) {
			try {
//				SimpleMailMessage mailMessage = new SimpleMailMessage();
//				mailMessage.setTo(user.getEmail());
//				mailMessage.setSubject("Verification Success!!!!");
//				mailMessage.setText("Login with your Email and Password from below link : "
//						+ "http://localhost:4200/page-not-found");
//				emailService.sendEmail(mailMessage);
				ConfirmationToken token=tokenRepo.findByUser(user);
				MailRequest ob=new MailRequest();
				ob.setFrom("nurecommercesite@gmail.com");
				ob.setTo(user.getEmail());
				ob.setName("Admin");
				ob.setSubject("Confirmation Email");
				Map<String, Object> model = new HashMap<>();
				model.put("Name", user.getName());
				model.put("token", token.getConfirmationToken());
				service.sendEmail(ob, model,"email-template.ftl");

			} catch (Exception e) {
				log.warn("Failed to create  User: ", e);
			}
		}
	/*************************************************************************
	 * Reject {@link Dealer}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	public User rejectDealer(String message,User user) {
		try {
			MailRequest ob=new MailRequest();
			ob.setFrom("nurecommercesite@gmail.com");
			ob.setTo(user.getEmail());
			ob.setName("Admin");
			ob.setSubject("Rejection Email");
			Map<String, Object> model = new HashMap<>();
			model.put("Name", user.getName());
			model.put("message", message);
			service.sendEmail(ob, model,"email-template-rejection.ftl");
			ConfirmationToken token=tokenRepo.findByUser(user);
			tokenRepo.delete(token);
			adminRepo.deleteById(user.getId());
			return user;
		} catch (Exception e) {
			log.warn("Failed to delete ", e);
			return user;
		}
	}
	
	/*************************************************************************
	 * Get Verified Dealer info {@link Dealer Info}
	 * 
	 * @return {@link DealerInfo}
	 *************************************************************************/

	public List<VerifiedDealerInfo> getVerifiedDealerInfo() {
		return vRepo.findAll();
	}
	/*************************************************************************
	 * Create a new VerifiedDealerInfo
	 * 
	 * @param ob {@link VerifiedDealerInfo} object
	 * @return {@link VerifiedDealerInfo}
	 *************************************************************************/
	@Override
	public VerifiedDealerInfo createVerifiedDealerInfo(VerifiedDealerInfo verifiedDealerInfo) {
		try {
			verifiedDealerInfo.setUser(adminRepo.findById(verifiedDealerInfo.getUserId()).orElse(null));
			return vRepo.save(verifiedDealerInfo);
		} catch (Exception e) {
			log.warn("Failed to create  Admin: ", e);
			return verifiedDealerInfo;
		}
	}
	
	
	public UserDto getUserDtoFromEntity(User ob) {
		if (ob.getImage() != null) {
			ob.setImageId(ob.getImage().getId());
		}
		UserDto obj = new UserDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
