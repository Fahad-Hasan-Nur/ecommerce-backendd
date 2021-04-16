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
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.RequisitionProduct;
import com.spring.ecommerce.model.Transaction;
import com.spring.ecommerce.model.ProductVariation;
import com.spring.ecommerce.service.RequisitionService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Product} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-03-11
 *************************************************************************/
@RestController
@RequestMapping("/api/requisition")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class RequisitionController {
	
	private final RequisitionService service;

	/*************************************************************************
	 * Create a new Requisition
	 * 
	 * @param ob {@link Requisition} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Requisition}
	 *************************************************************************/
	@PostMapping
	public Requisition addRequisition(@RequestBody Requisition requisition) {

		return service.create(requisition);
	}

	/*************************************************************************
	 * Get all Requisition {@link Requisition}
	 * 
	 * @return {@link List< Requisition>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<RequisitionDto> getAllRequisition() {
		return service.getAllRequisitions();
	}

	/*************************************************************************
	 * Get Requisition {@link Requisition} by Id
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	@GetMapping("/{id}")
	public RequisitionDto getRequisitionById(@PathVariable String id) {
		return service.getRequisitionById(id);
	}
	
	/*************************************************************************
	 * Get Requisition {@link Requisition} by On Cart
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	@GetMapping("/getOnCart/{id}")
	public List<RequisitionDto> getRequisitionByOnCart(@PathVariable String id) {
		return service.getRequisitionByOnCart(id);
	}
	
	/*************************************************************************
	 * Get Requisition {@link Requisition} by Status
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	@GetMapping("/getByStatus/{status}")
	public List<RequisitionDto> getRequisitionByStatus(@PathVariable String status) {
		return service.getRequisitionByStatus(status);
	}

	/*************************************************************************
	 * Update {@link Requisition}
	 * 
	 * @param ob {@link Requisition} object
	 * @return {@link Requisition}
	 *************************************************************************/
	@PutMapping
	public Requisition update(@RequestBody Requisition ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link Requisition}
	 * 
	 * @param ob {@link Requisition} object
	 * @return {@link Requisition}
	 *************************************************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	@GetMapping("/verifyRequisition/{id}")
	public Requisition verifyRequisition(@PathVariable String id) {
		return service.verifyRequisition(id);
	}
	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	@GetMapping("/completeRequisition/{id}")
	public Requisition completeRequisition(@PathVariable String id) {
		return service.completeRequisition(id);
	}
	/*************************************************************************
	 * Update Requisition Staus {@link Requisition}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/

	@GetMapping("/processRequisition/{id}")
	public Requisition processRequisition(@PathVariable String id) {
		return service.processRequisition(id);
	}
	
	/*************************************************************************
	 * Create  new RequisitionProducts
	 * 
	 * @param ob {@link RequisitionProducts} object
	 * @return {@link RequisitionProducts}
	 *************************************************************************/
	@PostMapping("/addRequisitionProduct")
	public List<RequisitionProduct> createRequisitionProduct(@RequestBody List<RequisitionProduct> requisitionProduct) {

		return service.createRequisitionProduct(requisitionProduct);
	}
	/*************************************************************************
	 * Get List RequisitionProduct {@link RequisitionProduct} by Requisition id
	 * 
	 * @return {@link List<RequisitionProduct>}
	 *************************************************************************/

	@GetMapping("/getAllRequisitionProduct/{id}")
	public List<RequisitionProduct> getRpByRequisitionId(@PathVariable String id) {
		return service.getRpByRequisitionId(id);
	}
	
	/*************************************************************************
	 * Get Requisition {@link Requisition} by Complete
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	@GetMapping("/getComplete/{id}")
	public List<RequisitionDto> getRequisitionByComplete(@PathVariable String id) {
		return service.getRequisitionByComplete(id);
	}
	
}
