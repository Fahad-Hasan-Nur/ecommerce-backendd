package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.ecommerce.dto.RequisitionDto;
import com.spring.ecommerce.dto.SubCategoryDto;
import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.SubCategory;
import com.spring.ecommerce.model.Transaction;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.RequisitionRepo;
import com.spring.ecommerce.repository.TransactionRepo;
import com.spring.ecommerce.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link TransactionServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-3-11
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements TransactionService {

	private final RequisitionRepo requisitionRepo;
	private final TransactionRepo transactionRepo;
	private final UserRepo userRepo;

	/*************************************************************************
	 * Create a new Transaction
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@Override
	public Transaction create(Transaction transaction) {
		try {
			transaction.setRequisition(requisitionRepo.findById(transaction.getRequisitionId()).orElse(null));
			transaction.setUser(userRepo.findById(transaction.getUserId()).orElse(null));
			return transactionRepo.save(transaction);
		} catch (Exception e) {
			log.warn("Failed to create  requisition: ", e);
			return transaction;
		}
	}

	/*************************************************************************
	 * Get Transaction {@link Transaction} by Requisition Id
	 * 
	 * @return {@link Requisition}
	 *************************************************************************/
	@Override
	public List<Transaction> getByRequisitionId(String id) {
		return transactionRepo.findAllByRequisition(requisitionRepo.findById(id).orElse(null)).stream()
				.map(this::getData).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get List<Transaction> {@link Transaction} by User Id
	 * 
	 * @return {@link List<Transaction>}
	 *************************************************************************/

	@Override
	public List<Transaction> getByUserId(String id) {
		return transactionRepo.findAllByUserOrderByIdDesc(userRepo.findById(id).orElse(null)).stream()
				.map(this::getData).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Update {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @return {@link Transaction}
	 *************************************************************************/
	@Override
	public Transaction update(Transaction transaction) {
		try {
			Transaction existingTransaction = transactionRepo.findById(transaction.getId()).orElse(null);
			transaction.setUser(userRepo.findById(transaction.getUserId()).orElse(null));
			transaction.setRequisition(requisitionRepo.findById(transaction.getTransactionId()).orElse(null));
			BeanUtils.copyProperties(transaction, existingTransaction);
			return transactionRepo.save(existingTransaction);
		} catch (Exception e) {
			log.warn("Failed to update  Requisition: ", e);
			return transaction;
		}
	}

	/*************************************************************************
	 * Delete {@link Transaction}
	 * 
	 * @param ob {@link Transaction} object
	 * @param rs
	 * @return {@link Transaction}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> deleteById(String id) {
		try {
			transactionRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to delete  Product: ", e);
			return new ResponseEntity<>("Unsccecfull.....!!!!!!!!!.", HttpStatus.BAD_REQUEST);
		}
	}

	/*************************************************************************
	 * Update transaction Staus {@link transaction}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/
	@Override
	public Transaction processTransaction(String id) {
		Transaction ob = transactionRepo.findById(id).orElse(null);
		ob.setStatus("Processing");
		transactionRepo.save(ob);
		return ob;
	}

	/*************************************************************************
	 * Update transaction Staus {@link transaction}
	 * 
	 * @return {@link transaction}
	 *************************************************************************/
	@Override
	public Transaction completeTransaction(String id) {
		Transaction ob = transactionRepo.findById(id).orElse(null);
		ob.setStatus("Complete");
		transactionRepo.save(ob);
		return ob;
	}
	
	/*************************************************************************
	 * Get Transaction {@link Transaction} by Status
	 * 
	 * @return {@link List<Transaction>}
	 *************************************************************************/
	@Override
	public List<Transaction> getTransactionByStatus(String status) {
		return transactionRepo.findAllByStatus(status).stream()
				.map(this::getData).collect(Collectors.toList());
	}

	public Transaction getData(Transaction ob) {
		ob.setRequisitionId(ob.getRequisition().getId());
		ob.setUserId(ob.getUser().getId());
		return ob;
	}
}
