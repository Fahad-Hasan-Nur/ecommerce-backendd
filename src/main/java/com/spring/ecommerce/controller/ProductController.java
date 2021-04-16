package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.ProductVariation;
import com.spring.ecommerce.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Product} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-29
 *************************************************************************/
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

	private final ProductServiceImpl service;

	/*************************************************************************
	 * Create a new Product
	 * 
	 * @param ob {@link Product} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Product}
	 *************************************************************************/
	@PostMapping
	public Product addProduct(@RequestBody Product product) {

		return service.create(product);
	}

	/*************************************************************************
	 * Get all Product {@link Product}
	 * 
	 * @return {@link List< Product>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<ProductDto> getAllProduct() {
		return service.getAllProducts();
	}

	/*************************************************************************
	 * Get all active {@link Product}
	 * 
	 * @return {@link List< Product>}
	 *************************************************************************/
	@GetMapping("/getAll/active")
	public List<ProductDto> getAllActiveProduct() {
		return service.getAllActiveProducts();
	}

	/*************************************************************************
	 * Get Product {@link Product} by Id
	 * 
	 * @return {@link Product}
	 *************************************************************************/

	@GetMapping("/{id}")
	public ProductDto getProductById(@PathVariable String id) {
		return service.getProductById(id);
	}
	
	/*************************************************************************
	 * Get List Product {@link Product} by Sub CategoryId
	 * 
	 * @return {@link List<Product>}
	 *************************************************************************/

	@GetMapping("/getAllBySubCategory/{id}")
	public List<ProductDto> getProductBySubCategoryId(@PathVariable String id) {
		return service.getProductBySubCategoryId(id);
	}

	/*************************************************************************
	 * Update {@link Product}
	 * 
	 * @param ob {@link Product} object
	 * @return {@link Product}
	 *************************************************************************/
	@PutMapping
	public Product update(@RequestBody Product ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link Product}
	 * 
	 * @param ob {@link Product} object
	 * @return {@link Product}
	 *************************************************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
	
	/*************************************************************************
	 * Create a new Variation
	 * 
	 * @param ob {@link ProductVariation} object
	 * @return {@link ProductVariation}
	 *************************************************************************/
	@PostMapping("/addVariation")
	public List<ProductVariation> createVariation(@RequestBody List<ProductVariation> variation) {

		return service.createVariation(variation);
	}
	
	/*************************************************************************
	 * Get List Variation {@link ProductVariation} by Product id
	 * 
	 * @return {@link List<Variation>}
	 *************************************************************************/

	@GetMapping("/getAllVariation/{id}")
	public List<ProductVariation> getVariationByProductId(@PathVariable String id) {
		return service.getVariationByProductId(id);
	}
	/*************************************************************************
	 * Update {@link ProductVariation}
	 * 
	 * @param ob {@link ProductVariation} object
	 * @return {@link ProductVariation}
	 *************************************************************************/
	@PutMapping("/updateVariation")
	public ProductVariation updateVariation(@RequestBody ProductVariation ob) {
		return service.updateVariation(ob);
	}
}
