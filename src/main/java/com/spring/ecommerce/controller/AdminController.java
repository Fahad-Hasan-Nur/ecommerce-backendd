package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.VerifiedDealerInfo;
import com.spring.ecommerce.service.AdminService;
import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Admin} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-02-13
 *************************************************************************/
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

	private final AdminService service;

	/*************************************************************************
	 * Create a new Admin
	 * 
	 * @param ob {@link Admin} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Admin}
	 *************************************************************************/
	@PostMapping
	public User addAdmin(@RequestBody @Validated User admin) {
		return service.create(admin);
	}

	/*************************************************************************
	 * Get all Admin {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@GetMapping("/getAll/")
	public List<UserDto> getAllAdmin() {
		return service.getAllAdmin();
	}
	
	/*************************************************************************
	 * Get all Active Dealer {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@GetMapping("/getAll/activeDealer/")
	public List<UserDto> getAllActiveDealers() {
		return service.getAllActiveDealers();
	}
	/*************************************************************************
	 * Get all Inactive Dealer {@link Admin}
	 * 
	 * @return {@link List< Admin>}
	 *************************************************************************/
	@GetMapping("/getAll/inactiveDealer/")
	public List<UserDto> getAllInactiveDealers() {
		return service.getAllInactiveDealers();
	}

	/*************************************************************************
	 * Get Admin {@link Admin} by Id
	 * 
	 * @return {@link Admin}
	 *************************************************************************/

	@GetMapping("/getById/{id}")
	public UserDto getUserDtoById(@PathVariable String id) {
		return service.getUserDtoById(id);
	}

	/*************************************************************************
	 * Get Admin {@link Admin} by Email
	 * 
	 * @return {@link Admin}
	 *************************************************************************/

	@GetMapping("/getByEmail/{email}")
	public UserDto getUserDtoByEmail(@PathVariable String email) {
		return service.getUserDtoByEmail(email);
	}
	
	/*************************************************************************
	 * Verify Dealer {@link Admin}
	 * 
	 * @return {@link Admin}
	 *************************************************************************/

	@GetMapping("/verifyDealer/{id}")
	public User verifyDealer(@PathVariable String id) {
		return service.verifyDealer(id);
	}
	/*************************************************************************
	 * Get Verified Dealer info {@link Admin}
	 * 
	 * @return {@link Admin}
	 *************************************************************************/

	@GetMapping("/verifiedDealerInfo")
	public List<VerifiedDealerInfo> getVerifiedDealerInfo() {
		return service.getVerifiedDealerInfo();
	}

	/*************************************************************************
	 * Update {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	@PutMapping
	public User update(@RequestBody User ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link Admin}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
	/*************************************************************************
	 * Reject {@link Dealer}
	 * 
	 * @param ob {@link Admin} object
	 * @return {@link Admin}
	 *************************************************************************/
	@PostMapping("/rejectDealer/{message}")
	public User rejectDealer(@PathVariable String message,@RequestBody User ob) {
		return service.rejectDealer(message,ob);
	}
	/*************************************************************************
	 * Create a new VerifiedDealerInfo
	 * 
	 * @param ob {@link VerifiedDealerInfo} object
	 * @return {@link VerifiedDealerInfo}
	 *************************************************************************/
	@PostMapping("/addVerifiedDealerInfo")
	public VerifiedDealerInfo addVerifiedDealerInfo(@RequestBody @Validated VerifiedDealerInfo verifiedDealerInfo) {
		return service.createVerifiedDealerInfo(verifiedDealerInfo);
	}
}
