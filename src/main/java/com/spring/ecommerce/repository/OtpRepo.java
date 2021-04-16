package com.spring.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Otp;

public interface OtpRepo extends JpaRepository<Otp, String> {

	Otp findByOtpAndMobileNumber(String otp, String mobileNumber);

}
