package com.spring.ecommerce.service;

import java.util.List;


import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.ProductVariation;

/*************************************************************************
 * {@link Product} service class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
public interface ProductService {

	/*************************************************************************
	 * Create a new Product
	 * 
	 * @param ob {@link Product} object
	 * @return {@link Product}
	 *************************************************************************/
	Product create(Product ob);

	/*************************************************************************
	 * Get all {@link Product}
	 * 
	 * @return {@link List<Product>}
	 *************************************************************************/
	List<ProductDto> getAllProducts();

	/*************************************************************************
	 * Get all {@link Product} by isActive Flag
	 * 
	 * @return {@link List<Product>}
	 *************************************************************************/
	List<ProductDto> getAllActiveProducts();

	/*************************************************************************
	 * Get Product {@link Product} by Id
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	ProductDto getProductById(String id);
	
	/*************************************************************************
	 * Get Product {@link Product} by SubCategoryId
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	List<ProductDto> getProductBySubCategoryId(String id);

	/*************************************************************************
	 * Update {@link Product}
	 * 
	 * @param ob {@link Product} object
	 * @param rs
	 * @return {@link Product}
	 *************************************************************************/
	Product update(Product ob);

	/*************************************************************************
	 * Delete {@link Product}
	 * 
	 * @param ob {@link Product} object
	 * @param rs
	 * @return {@link Product}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id);
	
	/*************************************************************************
	 * Create a new Variation
	 * 
	 * @param ob {@link ProductVariation} object
	 * @return {@link ProductVariation}
	 *************************************************************************/
	List<ProductVariation> createVariation(List<ProductVariation> ob);
	
	/*************************************************************************
	 * Get Variation {@link ProductVariation} by Product id
	 * 
	 * @return {@link ProductVariation}
	 *************************************************************************/
	List<ProductVariation> getVariationByProductId(String id);
	
	/*************************************************************************
	 * Update {@link ProductVariation}
	 * 
	 * @param ob {@link ProductVariation} object
	 * @param rs
	 * @return {@link ProductVariation}
	 *************************************************************************/
	ProductVariation updateVariation(ProductVariation ob);
}
