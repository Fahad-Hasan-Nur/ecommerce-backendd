package com.spring.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.ProductVariation;


public interface VariationRepo extends JpaRepository<ProductVariation, String> {

	List<ProductVariation> findAllByProduct(Product product);
}
