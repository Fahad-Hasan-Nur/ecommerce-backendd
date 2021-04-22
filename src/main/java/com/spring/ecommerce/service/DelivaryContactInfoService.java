package com.spring.ecommerce.service;

import com.spring.ecommerce.model.DelivaryContactInfo;

public interface DelivaryContactInfoService {

	/*************************************************************************
	 * Create a new DelivaryContactInfo
	 * 
	 * @param ob {@link DelivaryContactInfo} object
	 * @return {@link DelivaryContactInfo}
	 *************************************************************************/
	DelivaryContactInfo create(DelivaryContactInfo ob);
	
	/*************************************************************************
	 * Get DelivaryContactInfo {@link DelivaryContactInfo} by Id
	 * 
	 * @return {@link DelivaryContactInfo}
	 *************************************************************************/
	DelivaryContactInfo getById(String id);
}
