package com.spring.ecommerce.service;

import javax.servlet.http.HttpServletResponse;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Rating;

/*************************************************************************
 * {@link Rating} service class
 * 
 * @author Fahad Hasan
 * @since 2021-1-31
 *************************************************************************/
public interface RatingService {

	/*************************************************************************
	 * Create a new Rating
	 * 
	 * @param ob {@link Rating} object
	 * @return {@link Rating}
	 *************************************************************************/
	Rating create(Rating ob);

	/*************************************************************************
	 * Get average Rating {@link Rating} by Product
	 * 
	 * @return Average Rating
	 *************************************************************************/
	int getAverageRatingByProduct(String id);
	
	/*************************************************************************
	 * Get  Rating {@link Rating} by ProductId and UserId
	 * 
	 * @return  Rating
	 *************************************************************************/
	Rating getRatingByUserAndProduct(String pId, String uId);
	/*************************************************************************
	 * Update {@link Rating}
	 * 
	 * @param ob {@link Rating} object
	 * @param rs
	 * @return {@link Rating}
	 *************************************************************************/
	Rating update(Rating ob);
}
