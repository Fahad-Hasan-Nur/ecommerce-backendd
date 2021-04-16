package com.spring.ecommerce.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.configuration.TwilioConfiguration;
import com.spring.ecommerce.model.Otp;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.OtpRepo;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/*************************************************************************
 * {@link OtpServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-2-04
 *************************************************************************/
@Service
@Transactional
public class OtpServiceImpl implements OtpService {

	@Autowired
	OtpRepo otpRepo;
	
	@Autowired
	TwilioConfiguration twilioConfiguration;
	
	@Autowired
	public OtpServiceImpl(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration=twilioConfiguration;
	}
	
	
	/*************************************************************************
	 * Send OTP to Number
	 * 
	 * @path String number
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link OTP}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> sendOtp(String mobileNumber, HttpServletResponse rs) {
		Otp otp = new Otp(mobileNumber, String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000),
				System.currentTimeMillis() + 60000);
		Message.creator(new PhoneNumber(otp.getMobileNumber()), new PhoneNumber(twilioConfiguration.getTrial_number()),
				"Your OTP is:" + otp.getOtp()).create();
		otpRepo.save(otp);
		return new ResponseEntity<>("OTP send Succecfully.", HttpStatus.OK);
	}

	/*************************************************************************
	 * Verify OTP
	 * 
	 * @param ob {@link Product} object
	 * @return {@link OTP}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> verifyOtp(Otp ob) {
		Otp otp = otpRepo.findByOtpAndMobileNumber(ob.getOtp(), ob.getMobileNumber());
		System.out.print(ob);
		if (otp == null) {
			return new ResponseEntity<>("Enter valid Otp.", HttpStatus.BAD_REQUEST);
		}
		if (otp.getExpiryTime() >= System.currentTimeMillis()) {
			otpRepo.deleteById(otp.getId());
			return new ResponseEntity<>("OTP verified.", HttpStatus.OK);
		}
		otpRepo.deleteById(otp.getId());
		return new ResponseEntity<>("OTP expired Request a new One.", HttpStatus.BAD_GATEWAY);
	}

}
