package com.spring.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.RecommendationService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Recommendation} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-04-26
 *************************************************************************/
@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecommendationController {

	private final  RecommendationService service;
	/*************************************************************************
	 * Get List of Recommended Product {@link Product} 
	 * 
	 * @return {@link List<Product>}
	 * @throws TasteException 
	 * @throws IOException 
	 *************************************************************************/

	@GetMapping("/itemBased/{id}")
	public List<ProductDto> getItemBasedRecommendedProduct(@PathVariable String id) throws IOException, TasteException {
		return service.getItemBasedRecommendedProduct(id);
	}
	
	/*************************************************************************
	 * Get List of Recommended Product {@link Product} 
	 * 
	 * @return {@link List<Product>}
	 * @throws TasteException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 *************************************************************************/

	@GetMapping("/userBased/{id}")
	public List<ProductDto> getUserBasedRecommendedProduct(@PathVariable String id) throws NumberFormatException, TasteException, IOException {
		return service.getUserBasedRecommendedProduct(id);
	}
}
