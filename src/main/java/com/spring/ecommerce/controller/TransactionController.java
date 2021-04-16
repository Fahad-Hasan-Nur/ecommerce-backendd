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
import com.spring.ecommerce.model.Transaction;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.service.RequisitionService;
import com.spring.ecommerce.service.TransactionService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Transaction} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-03-11
 *************************************************************************/
@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransactionController {
	private final TransactionService service;

	/*************************************************************************
	 * Create a new Transaction
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@PostMapping
	public Transaction addTransaction(@RequestBody Transaction transaction) {

		return service.create(transaction);
	}


	/*************************************************************************
	 * Get Transaction {@link Transaction} by Requisition Id
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	@GetMapping("/getAllByRequisitionId/{id}")
	public List<Transaction> getByRequisitionId(@PathVariable String id) {
		return service.getByRequisitionId(id);
	}
	
	/*************************************************************************
	 * Get Transaction {@link Transaction} by User Id
	 * 
	 * @return {@link Transaction}
	 *************************************************************************/

	@GetMapping("/getAllByUserId/{id}")
	public List<Transaction> getByUserId(@PathVariable String id) {
		return service.getByUserId(id);
	}

	/*************************************************************************
	 * Update {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@PutMapping
	public Transaction update(@RequestBody Transaction ob) {
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
	 * Update transaction Staus {@link transaction}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/

	@GetMapping("/processTransaction/{id}")
	public Transaction processTransaction(@PathVariable String id) {
		return service.processTransaction(id);
	}
	/*************************************************************************
	 * Update transaction Staus {@link transaction}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/

	@GetMapping("/completeTransaction/{id}")
	public Transaction completeTransaction(@PathVariable String id) {
		return service.completeTransaction(id);
	}
	
	/*************************************************************************
	 * Get transaction {@link transaction} by Status
	 * 
	 * @return {@link transaction}
	 *************************************************************************/

	@GetMapping("/getByStatus/{status}")
	public List<Transaction> getTransactionByStatus(@PathVariable String status) {
		return service.getTransactionByStatus(status);
	}
	
}
