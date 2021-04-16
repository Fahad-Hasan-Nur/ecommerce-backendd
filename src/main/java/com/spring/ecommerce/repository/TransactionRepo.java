package com.spring.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.Transaction;
import com.spring.ecommerce.model.User;

public interface TransactionRepo  extends JpaRepository<Transaction, String> {

	Transaction findByRequisition(Requisition requisition);


	List<Transaction> findAllByRequisition(Requisition orElse);

	List<Transaction> findAllByUserOrderByIdDesc(User orElse);


	List<Transaction> findAllByStatus(String status);



}