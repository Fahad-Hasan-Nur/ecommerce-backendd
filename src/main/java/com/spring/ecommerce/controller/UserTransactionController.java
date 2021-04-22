package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.RequisitionDto;
import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.RequisitionProduct;
import com.spring.ecommerce.model.UserCartProduct;
import com.spring.ecommerce.service.RequisitionService;
import com.spring.ecommerce.service.UserCartProductService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link User Transaction} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-04-21
 *************************************************************************/
@RestController
@RequestMapping("/api/user/transaction")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserTransactionController {

	private final UserCartProductService service;

	/*************************************************************************
	 * Create a new UserCartProduct
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	@PostMapping("/userProduct/add")
	public UserCartProduct addUserCartProduct(@RequestBody UserCartProduct ob) {
		return service.create(ob);
	}

//	/*************************************************************************
//	 * Get all Requisition {@link Requisition}
//	 * 
//	 * @return {@link List< Requisition>}
//	 *************************************************************************/
//	@GetMapping("/getAll")
//	public List<RequisitionDto> getAllRequisition() {
//		return service.getAllRequisitions();
//	}
//
	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by Id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/

	@GetMapping("/getCartProduct/{id}")
	public UserCartProduct getUserCartProductById(@PathVariable String id) {
		return service.getUserCartProductById(id);
	}
	
	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by On Cart
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/

	@GetMapping("/getOnCart/{id}")
	public List<UserCartProduct> getUserCartProductByOnCart(@PathVariable String id) {
		return service.getUserCartProductByOnCart(id);
	}
	
	/*************************************************************************
	 * Get UserCartProduct {@link UserCartProduct} by order id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/

	@GetMapping("/getByOrder/{id}")
	public List<UserCartProduct> getByOrder(@PathVariable String id) {
		return service.getByOrder(id);
	}
	
	/*************************************************************************
	 * Update UserCartProduct status {@link UserCartProduct} by id
	 * 
	 * @return {@link UserCartProduct}
	 *************************************************************************/

	@PutMapping("/orderProduct")
	public UserCartProduct orderProduct(@RequestBody UserCartProduct ob) {
		return service.orderProduct(ob);
	}
//	
//	/*************************************************************************
//	 * Get Requisition {@link Requisition} by Status
//	 * 
//	 * @return {@link Requisition}
//	 *************************************************************************/
//
//	@GetMapping("/getByStatus/{status}")
//	public List<RequisitionDto> getRequisitionByStatus(@PathVariable String status) {
//		return service.getRequisitionByStatus(status);
//	}
//
	/*************************************************************************
	 * Update {@link UserCartProduct}
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	@PutMapping("/userProduct/update")
	public UserCartProduct update(@RequestBody UserCartProduct ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link UserCartProduct}
	 * 
	 * @param ob {@link UserCartProduct} object
	 * @return {@link UserCartProduct}
	 *************************************************************************/
	@DeleteMapping("/userProduct/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
//	/*************************************************************************
//	 * Update Requisition Staus {@link Requisition}
//	 * 
//	 * @return {@link Requisition}
//	 *************************************************************************/
//
//	@GetMapping("/verifyRequisition/{id}")
//	public Requisition verifyRequisition(@PathVariable String id) {
//		return service.verifyRequisition(id);
//	}
//	/*************************************************************************
//	 * Update Requisition Staus {@link Requisition}
//	 * 
//	 * @return {@link Requisition}
//	 *************************************************************************/
//
//	@GetMapping("/completeRequisition/{id}")
//	public Requisition completeRequisition(@PathVariable String id) {
//		return service.completeRequisition(id);
//	}
//	/*************************************************************************
//	 * Update Requisition Staus {@link Requisition}
//	 * 
//	 * @return {@link transaction}
//	 *************************************************************************/
//
//	@GetMapping("/processRequisition/{id}")
//	public Requisition processRequisition(@PathVariable String id) {
//		return service.processRequisition(id);
//	}
//	
//	/*************************************************************************
//	 * Create  new RequisitionProducts
//	 * 
//	 * @param ob {@link RequisitionProducts} object
//	 * @return {@link RequisitionProducts}
//	 *************************************************************************/
//	@PostMapping("/addRequisitionProduct")
//	public List<RequisitionProduct> createRequisitionProduct(@RequestBody List<RequisitionProduct> requisitionProduct) {
//
//		return service.createRequisitionProduct(requisitionProduct);
//	}
//	/*************************************************************************
//	 * Get List RequisitionProduct {@link RequisitionProduct} by Requisition id
//	 * 
//	 * @return {@link List<RequisitionProduct>}
//	 *************************************************************************/
//
//	@GetMapping("/getAllRequisitionProduct/{id}")
//	public List<RequisitionProduct> getRpByRequisitionId(@PathVariable String id) {
//		return service.getRpByRequisitionId(id);
//	}
//	
//	/*************************************************************************
//	 * Get Requisition {@link Requisition} by Complete
//	 * 
//	 * @return {@link Requisition}
//	 *************************************************************************/
//
//	@GetMapping("/getComplete/{id}")
//	public List<RequisitionDto> getRequisitionByComplete(@PathVariable String id) {
//		return service.getRequisitionByComplete(id);
//	}
	
}

