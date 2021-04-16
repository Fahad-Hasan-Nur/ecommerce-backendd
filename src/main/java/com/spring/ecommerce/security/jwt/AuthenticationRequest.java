package com.spring.ecommerce.security.jwt;

import lombok.Data;

@Data
public class AuthenticationRequest {

	public AuthenticationRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public AuthenticationRequest() {};

	private String email;
	private String password;
}
