package com.spring.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.model.DelivaryLocationInfo;
import com.spring.ecommerce.repository.DelivaryLocationInfoRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link DelivaryLocationInfoService} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-4-22
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DelivaryLocationInfoServiceImpl implements DelivaryLocationInfoService{
	
	private final DelivaryLocationInfoRepo repo;

	/*************************************************************************
	 * Create a new DelivaryLocationInfo
	 * 
	 * @param ob {@link DelivaryLocationInfo} object
	 * @return {@link DelivaryLocationInfo}
	 *************************************************************************/
	
	public DelivaryLocationInfo create(DelivaryLocationInfo ob) {
		try {
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			return ob;
		}
	}
	
	/*************************************************************************
	 * Get DelivaryLocationInfo {@link DelivaryLocationInfo} by Id
	 * 
	 * @return {@link DelivaryLocationInfo}
	 *************************************************************************/
	public DelivaryLocationInfo getById(String id) {
		return repo.findById(id).orElse(null);
	}
}
