package com.spring.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Requisition;
import com.spring.ecommerce.model.RequisitionProduct;

public interface RequisitionProductRepo extends JpaRepository<RequisitionProduct, String> {

	List<RequisitionProduct> findAllByRequisition(Requisition orElse);


}
