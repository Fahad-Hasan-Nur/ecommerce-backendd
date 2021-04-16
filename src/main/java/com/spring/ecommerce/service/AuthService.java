package com.spring.ecommerce.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.model.User;
import com.spring.ecommerce.security.jwt.AuthenticationRequest;

/*************************************************************************
 * {@link Auth} service class
 * 
 * @author Fahad Hasan
 * @since 2021-02-03
 *************************************************************************/
public interface AuthService {

	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @return {@link User}
	 *************************************************************************/
	ResponseEntity<?> create(User ob);

	/*************************************************************************
	 * Activate newly created USer
	 * 
	 * @param ob {@link Token} String
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link String}
	 *************************************************************************/
	User confirmUserAccount(String token);

	/*************************************************************************
	 * User Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 * @throws Exception
	 *************************************************************************/
	ResponseEntity<?> createTokenForUser(AuthenticationRequest authenticationRequest) throws Exception;

	/*************************************************************************
	 * Admin Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 * @throws Exception
	 *************************************************************************/
	ResponseEntity<?> createTokenForAdmin(AuthenticationRequest authenticationRequest) throws Exception;
	/*************************************************************************
	 * Initialize First super admin
	 * 
	 * @param ob {@link User} object
	 * @throws Exception 
	 *************************************************************************/
	 ResponseEntity<?> initializeAdmin() throws Exception;
}
