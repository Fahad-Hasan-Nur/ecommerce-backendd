package com.spring.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.model.DelivaryContactInfo;
import com.spring.ecommerce.repository.DelivaryContactInfoRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link DelivaryContactInfoService} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-4-22
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DelivaryContactInfoServiceImpl implements  DelivaryContactInfoService{

	private final DelivaryContactInfoRepo repo;

	/*************************************************************************
	 * Create a new DelivaryContactInfo
	 * 
	 * @param ob {@link DelivaryContactInfo} object
	 * @return {@link DelivaryContactInfo}
	 *************************************************************************/
	
	public DelivaryContactInfo create(DelivaryContactInfo ob) {
		try {
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  DelivaryContactInfo: ", e);
			return ob;
		}
	}
	
	/*************************************************************************
	 * Get DelivaryContactInfo {@link DelivaryContactInfo} by Id
	 * 
	 * @return {@link DelivaryContactInfo}
	 *************************************************************************/
	public DelivaryContactInfo getById(String id) {
		return repo.findById(id).orElse(null);
	}
}
