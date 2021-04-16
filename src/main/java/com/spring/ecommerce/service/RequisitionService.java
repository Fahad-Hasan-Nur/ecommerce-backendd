package com.spring.ecommerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.dto.RequisitionDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.RequisitionProduct;

/*************************************************************************
 * {@link Product} service class
 * 
 * @author Fahad Hasan
 * @since 2021-3-11
 *************************************************************************/
public interface RequisitionService {

	/*************************************************************************
	 * Create a new Requisition
	 * 
	 * @param ob {@link Requisition} object
	 * @return {@link Requisition}
	 *************************************************************************/
	Requisition create(Requisition ob);

	/*************************************************************************
	 * Get all {@link Requisition}
	 * 
	 * @return {@link List<Requisition>}
	 *************************************************************************/
	List<RequisitionDto> getAllRequisitions();

	/*************************************************************************
	 * Get Requisition {@link Requisition} by Id
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/
	RequisitionDto getRequisitionById(String id);

	/*************************************************************************
	 * Get Requisition {@link Requisition} by On Cart
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/
	List<RequisitionDto> getRequisitionByOnCart(String id);

	/*************************************************************************
	 * Get Requisition {@link Requisition} by Status
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/
	List<RequisitionDto> getRequisitionByStatus(String status);

	/*************************************************************************
	 * Update {@link Requisition}
	 * 
	 * @param ob {@link Requisition} object
	 * @param rs
	 * @return {@link Requisition}
	 *************************************************************************/
	Requisition update(Requisition ob);

	/*************************************************************************
	 * Delete {@link Requisition}
	 * 
	 * @param ob {@link Requisition} object
	 * @param rs
	 * @return {@link Requisition}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id);

	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	Requisition verifyRequisition(String id);

	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	Requisition completeRequisition(String id);

	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/

	Requisition processRequisition(String id);

	/*************************************************************************
	 * Create new RequisitionProducts
	 * 
	 * @param ob {@link RequisitionProducts} object
	 * @return {@link RequisitionProducts}
	 *************************************************************************/
	List<RequisitionProduct> createRequisitionProduct(List<RequisitionProduct> requisitionProduct);
	
	/*************************************************************************
	 * Get List RequisitionProduct {@link RequisitionProduct} by Requisition id
	 * 
	 * @return {@link List<RequisitionProduct>}
	 *************************************************************************/

	List<RequisitionProduct> getRpByRequisitionId( String id);
	
	/*************************************************************************
	 * Get Requisition {@link Requisition} by Complete
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/
	List<RequisitionDto> getRequisitionByComplete(String id);
}
