package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.model.UserOrder;
import com.spring.ecommerce.repository.DelivaryContactInfoRepo;
import com.spring.ecommerce.repository.DelivaryLocationInfoRepo;
import com.spring.ecommerce.repository.UserOrderRepo;
import com.spring.ecommerce.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link UserOrderService} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-4-22
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserOrderServiceImpl implements UserOrderService {
	
	private final UserOrderRepo repo;
	private final UserRepo userRepo;
	private final DelivaryContactInfoRepo delivaryContactInfoRepo;
	private final DelivaryLocationInfoRepo delivaryLocationInfoRepo;


	/*************************************************************************
	 * Create a new UserOrder
	 * 
	 * @param ob {@link UserOrder} object
	 * @return {@link UserOrder}
	 *************************************************************************/
	public UserOrder add(UserOrder ob) {
		try {
			System.out.println(ob);
			ob.setUser(userRepo.findById(ob.getUserId()).orElse(null));
			ob.setDelivaryContactInfo(delivaryContactInfoRepo.findById(ob.getDelivaryContactInfoId()).orElse(null));
			ob.setDelivaryLocationInfo(delivaryLocationInfoRepo.findById(ob.getDelivaryLocationInfoId()).orElse(null));
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  UserOrder: ", e);
			return ob;
		}
	}

	/*************************************************************************
	 * Get all UserOrder {@link UserOrder}
	 * 
	 * @return {@link List< UserOrder>}
	 *************************************************************************/
	 public List<UserOrder> getAll(){
			return repo.findAll().stream().map(this::getData).collect(Collectors.toList());
	 }

	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	 public UserOrder getById(String id) {
		 return repo.findById(id).orElse(null);
	 }
	
	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by User Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	 public List<UserOrder> getByUserId(String id) {
		 return repo.findAllByUser(userRepo.findById(id).orElse(null)).stream().map(this::getData).collect(Collectors.toList());
	 }
	 
	 UserOrder getData(UserOrder ob){
		 ob.setUserId(ob.getUser().getId());
		 ob.setDelivaryContactInfoId(ob.getDelivaryContactInfo().getId());
		 ob.setDelivaryLocationInfoId(ob.getDelivaryLocationInfo().getId());
		 return ob;
	 }
	 
}
