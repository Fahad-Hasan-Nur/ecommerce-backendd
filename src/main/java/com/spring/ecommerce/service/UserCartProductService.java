package com.spring.ecommerce.service;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.ecommerce.model.UserCartProduct;

/*************************************************************************
 * {@link UserCartProduct} service class
 * 
 * @author Fahad Hasan
 * @since 2021-4-21
 *************************************************************************/
public interface UserCartProductService {
	/*************************************************************************
	 * Create a new UserCartProduct
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	UserCartProduct create(UserCartProduct ob);

	/*************************************************************************
	 * Update UserCartProduct status {@link UserCartProduct} by id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/

	 UserCartProduct orderProduct( UserCartProduct ob);

	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by Id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	UserCartProduct getUserCartProductById(String id);

	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by On Cart
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	List<UserCartProduct> getUserCartProductByOnCart(String id);
	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by order id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	 List<UserCartProduct> getByOrder( String id);

	/*************************************************************************
	 * Update {@link UserCartProduct}
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @param rs
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	UserCartProduct update(UserCartProduct ob);

	/*************************************************************************
	 * Delete {@link UserCartProduct}
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @param rs
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id);
//
//	/*************************************************************************
//	 * Update Requisition Staus {@link Requisition}
//	 * 
//	 * @return {@link Requisition}
//	 *************************************************************************/
//
//	Requisition verifyRequisition(String id);
//
//	/*************************************************************************
//	 * Update Requisition Staus {@link Requisition}
//	 * 
//	 * @return {@link Requisition}
//	 *************************************************************************/
//
//	Requisition completeRequisition(String id);
//
//	/*************************************************************************
//	 * Update Requisition Staus {@link Requisition}
//	 * 
//	 * @return {@link transaction}
//	 *************************************************************************/
//
//	Requisition processRequisition(String id);
//
//	/*************************************************************************
//	 * Create new RequisitionProducts
//	 * 
//	 * @param ob {@link RequisitionProducts} object
//	 * @return {@link RequisitionProducts}
//	 *************************************************************************/
//	List<RequisitionProduct> createRequisitionProduct(List<RequisitionProduct> requisitionProduct);
//	
//	/*************************************************************************
//	 * Get List RequisitionProduct {@link RequisitionProduct} by Requisition id
//	 * 
//	 * @return {@link List<RequisitionProduct>}
//	 *************************************************************************/
//
//	List<RequisitionProduct> getRpByRequisitionId( String id);
//	
//	/*************************************************************************
//	 * Get Requisition {@link Requisition} by Complete
//	 * 
//	 * @return {@link Requisition}
//	 *************************************************************************/
//	List<RequisitionDto> getRequisitionByComplete(String id);
}

