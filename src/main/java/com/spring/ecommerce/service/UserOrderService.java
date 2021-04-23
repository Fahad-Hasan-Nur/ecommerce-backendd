package com.spring.ecommerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.ecommerce.model.UserOrder;

/*************************************************************************
 * {@link UserOrder} service class
 * 
 * @author Fahad Hasan
 * @since 2021-4-22
 *************************************************************************/
public interface UserOrderService {

	/*************************************************************************
	 * Create a new UserOrder
	 * 
	 * @param ob {@link UserOrder} object
	 * @return {@link UserOrder}
	 *************************************************************************/
	UserOrder add(UserOrder ob);

	/*************************************************************************
	 * Get all UserOrder {@link UserOrder}
	 * 
	 * @return {@link List< UserOrder>}
	 *************************************************************************/
	List<UserOrder> getAll();

	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	UserOrder getById(String id);

	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by User Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	List<UserOrder> getByUserId(String id);

	/*************************************************************************
	 * Get all UserOrder {@link UserOrder} by status
	 * 
	 * @return {@link List< UserOrder>}
	 *************************************************************************/
	List<UserOrder> getByStatus(String status);

	/*************************************************************************
	 * Update {@link UserOrder Status}
	 * 
	 * @param ob {@link UserOrder} object
	 * @return {@link UserOrder}
	 *************************************************************************/
	UserOrder update(UserOrder ob);
	/*************************************************************************
	 * Delete {@link UserOrder}
	 * 
	 * @param ob {@link UserOrder} object
	 * @return {@link UserOrder}
	 *************************************************************************/
	 ResponseEntity<?> delete( String id);
}
