package com.spring.ecommerce.service;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.ecommerce.model.UserCartProduct;
import com.spring.ecommerce.repository.ProductRepo;
import com.spring.ecommerce.repository.UserCartProductRepo;
import com.spring.ecommerce.repository.UserRepo;
import com.spring.ecommerce.repository.VariationRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link UserCartProductServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-4-21
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCartProductServiceImpl implements UserCartProductService {
	


	private final UserCartProductRepo repo;
	private final UserRepo userRepo;
	private final ProductRepo productRepo;
	private final VariationRepo variationRepo;


	/*************************************************************************
	 * Create a new UserCartProduct
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	@Override
	public UserCartProduct create(UserCartProduct ob) {
		try {
			ob=setData(ob);
			return repo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  requisition: ", e);
			return ob;
		}
	}
	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by UserCartProduct Id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	@Override
	public UserCartProduct getUserCartProductById(String id) {
		return repo.findById(id).map(this::getData).orElse(null);
	}

	/*************************************************************************
	 * Get List<UserCartProduct> {@link UserCartProduct} by User Id
	 * 
	 * @return {@link List<UserCartProduct>}
	 *************************************************************************/

	@Override
	public List<UserCartProduct> getUserCartProductByOnCart(String id) {
		return repo.findAllByUserAndStatus(userRepo.findById(id).orElse(null),"CART").stream()
				.map(this::getData).collect(Collectors.toList());
	}
	
	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by order id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/

	 public List<UserCartProduct> getByOrder( String id){
		 return repo.findAllByOrderId(id).stream()
					.map(this::getData).collect(Collectors.toList());
	 }

	/*************************************************************************
	 * Update {@link UserCartProduct}
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	@Override
	public UserCartProduct update(UserCartProduct ob) {
		try {
			UserCartProduct existingOb = repo.findById(ob.getId()).orElse(null);
			ob=setData(ob);
			BeanUtils.copyProperties(ob, existingOb);
			return repo.save(existingOb);
		} catch (Exception e) {
			log.warn("Failed to update  Requisition: ", e);
			return ob;
		}
	}

	/*************************************************************************
	 * Delete {@link UserCartProduct}
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @param rs
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> deleteById(String id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to delete  UserCartProduct: ", e);
			return new ResponseEntity<>("Unsccecfull.....!!!!!!!!!.", HttpStatus.BAD_REQUEST);
		}
	}
	/*************************************************************************
	 * Update UserCartProduct status {@link UserCartProduct} by id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/

	public UserCartProduct orderProduct( UserCartProduct ob) {
		UserCartProduct existingOb=repo.findById(ob.getId()).orElse(null);
		existingOb.setStatus("ORDERED");
		existingOb.setOrderId(ob.getOrderId());
		return repo.save(existingOb);
	}
//
//	/*************************************************************************
//	 * Update transaction Staus {@link transaction}
//	 * 
//	 * @return {@link transaction}
//	 *************************************************************************/
//	@Override
//	public Transaction processTransaction(String id) {
//		Transaction ob = transactionRepo.findById(id).orElse(null);
//		ob.setStatus("Processing");
//		transactionRepo.save(ob);
//		return ob;
//	}
//
//	/*************************************************************************
//	 * Update transaction Staus {@link transaction}
//	 * 
//	 * @return {@link transaction}
//	 *************************************************************************/
//	@Override
//	public Transaction completeTransaction(String id) {
//		Transaction ob = transactionRepo.findById(id).orElse(null);
//		ob.setStatus("Complete");
//		transactionRepo.save(ob);
//		return ob;
//	}
//	
//	/*************************************************************************
//	 * Get Transaction {@link Transaction} by Status
//	 * 
//	 * @return {@link List<Transaction>}
//	 *************************************************************************/
//	@Override
//	public List<Transaction> getTransactionByStatus(String status) {
//		return transactionRepo.findAllByStatus(status).stream()
//				.map(this::getData).collect(Collectors.toList());
//	}
//
	public UserCartProduct setData(UserCartProduct ob) {
		ob.setUser(userRepo.findById(ob.getUserId()).orElse(null));
		ob.setProduct(productRepo.findById(ob.getProductId()).orElse(null));
		ob.setVariation(variationRepo.findById(ob.getVariationId()).orElse(null));
		return ob;
	}
	
	public UserCartProduct getData(UserCartProduct ob) {
		ob.setUserId(ob.getUser().getId());
		ob.setProductId(ob.getProduct().getId());
		ob.setProductName(ob.getProduct().getName());
		ob.setVariationId(ob.getVariation().getId());
		ob.setVariationName(ob.getVariation().getName());
		ob.setUserId(ob.getUser().getId());
		return ob;
	}
}
