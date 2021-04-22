package com.spring.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.ecommerce.model.DelivaryContactInfo;

public interface DelivaryContactInfoRepo  extends JpaRepository<DelivaryContactInfo, String>  {

}
