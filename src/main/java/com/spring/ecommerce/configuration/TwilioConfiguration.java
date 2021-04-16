package com.spring.ecommerce.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

	private String trial_number;
	private String auth_token;
	private String account_sid;
	
	public TwilioConfiguration() {}
}
