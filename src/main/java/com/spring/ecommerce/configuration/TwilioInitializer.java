package com.spring.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TwilioInitializer {

	@Autowired
	TwilioConfiguration twilioConfiguration;

	@Autowired
	public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration = twilioConfiguration;
		Twilio.init(twilioConfiguration.getAccount_sid(), twilioConfiguration.getAuth_token());
		log.info("Twilio initialized....");
	}
}
