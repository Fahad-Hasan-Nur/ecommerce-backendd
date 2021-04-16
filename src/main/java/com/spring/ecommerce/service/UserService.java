package com.spring.ecommerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.model.SubCategory;
import com.spring.ecommerce.model.User;

/*************************************************************************
 * {@link User} service class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
public interface UserService {
	// TODO Will be removed
	List<UserDto> getAllUser();
	/*************************************************************************
	 * Delete {@link User}
	 * 
	 * @param ob {@link User} object
	 * @param rs
	 * @return {@link SubCategory}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id);

	/*************************************************************************
	 * Get User {@link User} by Email
	 * 
	 * @return {@link User}
	 *************************************************************************/
	User getUserByEmail(String email);
}
