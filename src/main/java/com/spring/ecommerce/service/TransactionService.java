package com.spring.ecommerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.ecommerce.dto.RequisitionDto;
import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.Transaction;

/*************************************************************************
 * {@link Transaction} service class
 * 
 * @author Fahad Hasan
 * @since 2021-3-11
 *************************************************************************/
public interface TransactionService {
	/*************************************************************************
	 * Create a new Transaction
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	Transaction create(Transaction ob);

	/*************************************************************************
	 * Get Transaction {@link Transaction} by Requisition Id
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/

	 List<Transaction> getByRequisitionId(String id) ;
	
	/*************************************************************************
	 * Get Transaction {@link Transaction} by User Id
	 * 
	 * @return {@link Transaction}
	 *************************************************************************/

	 List<Transaction> getByUserId( String id);

	/*************************************************************************
	 * Update {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	Transaction update(Transaction ob);

	/*************************************************************************
	 * Delete {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @param rs
	 * @return {@link Transaction}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id);
	
	/*************************************************************************
	 * Update transaction Staus {@link transaction}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/

	public Transaction processTransaction(String id);
	/*************************************************************************
	 * Update transaction Staus {@link transaction}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/

	public Transaction completeTransaction(String id) ;
	
	/*************************************************************************
	 * Get transaction {@link transaction} by Status
	 * 
	 * @return {@link transaction}
	 *************************************************************************/
	List<Transaction> getTransactionByStatus(String status);
}
