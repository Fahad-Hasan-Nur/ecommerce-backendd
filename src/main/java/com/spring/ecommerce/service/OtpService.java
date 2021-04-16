package com.spring.ecommerce.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import com.spring.ecommerce.model.Otp;
import com.spring.ecommerce.model.Product;

/*************************************************************************
 * {@link Otp} service class
 * 
 * @author Fahad Hasan
 * @since 2021-2-04
 *************************************************************************/
public interface OtpService {

	/*************************************************************************
	 * Send OTP to Number
	 * 
	 * @path String number
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link OTP}
	 *************************************************************************/
	public ResponseEntity<?> sendOtp(String mobileNumber, HttpServletResponse rs);

	/*************************************************************************
	 * Verify OTP
	 * 
	 * @param ob {@link Product} object
	 * @return {@link OTP}
	 *************************************************************************/
	public ResponseEntity<?> verifyOtp(Otp ob);
}
