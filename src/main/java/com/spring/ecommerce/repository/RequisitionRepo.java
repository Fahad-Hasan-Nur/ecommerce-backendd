package com.spring.ecommerce.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.dto.RequisitionDto;
import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.User;

public interface RequisitionRepo extends JpaRepository<Requisition, String> {

	List<Requisition> findAllByStatusAndUser(String status,User user);

	List<Requisition> findAllByStatus(String status);


}
