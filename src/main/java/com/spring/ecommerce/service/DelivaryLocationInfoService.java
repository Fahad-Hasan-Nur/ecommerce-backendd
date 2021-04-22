package com.spring.ecommerce.service;

import com.spring.ecommerce.model.DelivaryLocationInfo;

public interface DelivaryLocationInfoService {

	/*************************************************************************
	 * Create a new DelivaryLocationInfo
	 * 
	 * @param ob {@link DelivaryLocationInfo} object
	 * @return {@link DelivaryLocationInfo}
	 *************************************************************************/
	DelivaryLocationInfo create(DelivaryLocationInfo ob);
	
	/*************************************************************************
	 * Get DelivaryLocationInfo {@link DelivaryLocationInfo} by Id
	 * 
	 * @return {@link DelivaryLocationInfo}
	 *************************************************************************/
	DelivaryLocationInfo getById(String id);
}
