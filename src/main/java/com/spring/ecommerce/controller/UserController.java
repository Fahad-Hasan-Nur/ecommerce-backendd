package com.spring.ecommerce.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link User} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-02-01
 *************************************************************************/
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	private final UserService service;

	// TODO remove
	@GetMapping("/getAll")
	public List<UserDto> getAllUser() {
		return service.getAllUser();
	}
	/*************************************************************************
	 * Delete {@link User}
	 * 
	 * @param ob {@link User} object
	 * @return {@link User}
	 *************************************************************************/
	@DeleteMapping("/getById/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
	
	/*************************************************************************
	 * Get User {@link User} by Email
	 * 
	 * @return {@link User}
	 *************************************************************************/

	@GetMapping("/getByEmail/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return service.getUserByEmail(email);
	}

}
