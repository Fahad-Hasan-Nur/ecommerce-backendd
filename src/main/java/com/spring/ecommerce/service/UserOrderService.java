package com.spring.ecommerce.service;

import java.util.List;


import com.spring.ecommerce.model.UserOrder;

/*************************************************************************
 * {@link  UserOrder} service class
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
	 UserOrder add( UserOrder ob);

	/*************************************************************************
	 * Get all UserOrder {@link UserOrder}
	 * 
	 * @return {@link List< UserOrder>}
	 *************************************************************************/
	 List<UserOrder> getAll() ;

	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	 UserOrder getById(String id) ;
	
	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by User Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	 List<UserOrder> getByUserId(String id) ;
}
