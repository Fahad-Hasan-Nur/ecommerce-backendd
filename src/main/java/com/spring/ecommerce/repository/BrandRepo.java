package com.spring.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Brand;


public interface BrandRepo extends JpaRepository<Brand, String> {

	List<Brand> findAllByIsActiveOrderByIdDesc(boolean isActive);
}
