package com.spring.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;

/*************************************************************************
 * {@link Recommendation} service class
 * 
 * @author Fahad Hasan
 * @since 2021-4-26
 *************************************************************************/
public interface RecommendationService {

	/*************************************************************************
	 * Get List of Recommended Product {@link Product} 
	 * 
	 * @return {@link List<Product>}
	 * @throws TasteException 
	 * @throws IOException 
	 *************************************************************************/

	 List<ProductDto> getItemBasedRecommendedProduct( String prodyctId) throws IOException, TasteException;
	
	/*************************************************************************
	 * Get List of Recommended Product {@link Product} 
	 * 
	 * @return {@link List<Product>}
	 * @throws TasteException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 *************************************************************************/

	 List<ProductDto> getUserBasedRecommendedProduct(String userId) throws NumberFormatException, TasteException, IOException ;
}
