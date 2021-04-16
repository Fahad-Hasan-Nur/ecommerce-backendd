package com.spring.ecommerce.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.model.Otp;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.OtpService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Otp} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-02-03
 *************************************************************************/
@RestController
@RequestMapping("/api/auth/otp")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class OtpController {

	private final OtpService service;

	/*************************************************************************
	 * Send OTP to Number
	 * 
	 * @path String number
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link OTP}
	 *************************************************************************/
	@PostMapping("/sendOtp/{mobileNumber}")
	public ResponseEntity<?> sendOtp(@PathVariable String mobileNumber, HttpServletResponse rs) {
		return service.sendOtp(mobileNumber, rs);
	}

	/*************************************************************************
	 * Verify OTP
	 * 
	 * @param ob {@link Product} object
	 * @return {@link OTP}
	 *************************************************************************/
	@GetMapping("/varifyOtp")
	public ResponseEntity<?> verifyOtp(@RequestBody Otp ob) {
		return service.verifyOtp(ob);
	}

}
