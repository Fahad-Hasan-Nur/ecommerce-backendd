package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.ProductVariation;
import com.spring.ecommerce.model.UserCartProduct;
import com.spring.ecommerce.model.UserOrder;
import com.spring.ecommerce.repository.DelivaryContactInfoRepo;
import com.spring.ecommerce.repository.DelivaryLocationInfoRepo;
import com.spring.ecommerce.repository.UserCartProductRepo;
import com.spring.ecommerce.repository.UserOrderRepo;
import com.spring.ecommerce.repository.UserRepo;
import com.spring.ecommerce.repository.VariationRepo;

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
	private final UserCartProductRepo userCartProductRepo;
	private final VariationRepo variationRepo;

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
	public List<UserOrder> getAll() {
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
		return repo.findAllByUserOrderByCreatedAtDesc(userRepo.findById(id).orElse(null)).stream().map(this::getData)
				.collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all UserOrder {@link UserOrder} by status
	 * 
	 * @return {@link List< UserOrder>}
	 *************************************************************************/
	public List<UserOrder> getByStatus(String status) {
		return repo.findAllByStatus(status).stream().map(this::getData).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Update {@link UserOrder Status}
	 * 
	 * @param ob {@link UserOrder} object
	 * @return {@link UserOrder}
	 *************************************************************************/
	public UserOrder update(UserOrder ob) {
		try {
			UserOrder existingOb = repo.findById(ob.getId()).orElse(null);
			ob.setDelivaryContactInfo(delivaryContactInfoRepo.findById(ob.getDelivaryContactInfoId()).orElse(null));
			ob.setDelivaryLocationInfo(delivaryLocationInfoRepo.findById(ob.getDelivaryLocationInfoId()).orElse(null));
			ob.setUser(userRepo.findById(ob.getUserId()).orElse(null));
			BeanUtils.copyProperties(ob, existingOb);
			repo.save(existingOb);
			System.out.println(ob.getStatus());
			if(ob.getStatus().equals("COMPLETE")==true) {
				System.out.println(ob.getStatus());
				reduceQuantity(ob.getId());
			}
			return existingOb;
		} catch (Exception e) {
			log.warn("Failed to update  Product: ", e);
			return ob;
		}
	}

	/*************************************************************************
	 * Delete {@link UserOrder}
	 * 
	 * @param ob {@link UserOrder} object
	 * @return {@link UserOrder}
	 *************************************************************************/
	public ResponseEntity<?> delete(String id) {
		try {
			UserOrder ob = repo.findById(id).orElse(null);
			delivaryContactInfoRepo.delete(ob.getDelivaryContactInfo());
			delivaryLocationInfoRepo.delete(ob.getDelivaryLocationInfo());
			userCartProductRepo.deleteAll(userCartProductRepo.findAllByOrderId(id));
			repo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to delete  Order: ", e);
			return new ResponseEntity<>("Unsccecfull.....!!!!!!!!!.", HttpStatus.BAD_REQUEST);
		}
	}

	UserOrder getData(UserOrder ob) {
		ob.setUserId(ob.getUser().getId());
		ob.setDelivaryContactInfoId(ob.getDelivaryContactInfo().getId());
		ob.setDelivaryLocationInfoId(ob.getDelivaryLocationInfo().getId());
		return ob;
	}
	
	void reduceQuantity(String id){
		List<UserCartProduct> userCartProduct =userCartProductRepo.findAllByOrderId(id);
		for(UserCartProduct ob:userCartProduct) {
			System.out.println(ob.getQuantity());

			ProductVariation variation=ob.getVariation();
			variation.setQuantity(variation.getQuantity()-ob.getQuantity());
			System.out.println(variation.getQuantity());

			variationRepo.save(variation);
		}
	}

}
