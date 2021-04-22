package com.spring.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.ecommerce.model.DelivaryLocationInfo;

public interface DelivaryLocationInfoRepo extends JpaRepository<DelivaryLocationInfo, String> {

}
