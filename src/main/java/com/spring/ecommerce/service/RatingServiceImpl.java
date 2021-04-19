package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Rating;
import com.spring.ecommerce.repository.ProductRepo;
import com.spring.ecommerce.repository.RatingRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link RatingServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-31
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RatingServiceImpl implements RatingService {

	private final RatingRepo ratingRepo;
	private final ProductRepo productRepo;


	/*************************************************************************
	 * Create a new Rating
	 * 
	 * @param ob {@link Rating} object
	 * @return {@link Rating}
	 *************************************************************************/
	@Override
	public Rating create(Rating ob) {
		try {
			ratingRepo.save(ob);
			updateProductAvgRating(ob.getProductId());
			return ratingRepo.save(ob);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			return ob;
		}
	}

	/*************************************************************************
	 * Get average Rating {@link Rating} by Product
	 * 
	 * @return Average Rating
	 ************************************************************************/
	@Override
	public int getAverageRatingByProduct(String pId) {
		int averageRating = 0;
		List<Rating> ratings = ratingRepo.findAllByProductId(pId);
		for (Rating ob : ratings) {
			averageRating += ob.getRatingValue();
		}
		if (ratings.size() > 0) {
			averageRating = averageRating / ratings.size();
			return averageRating;
		} else {
			return 0;
		}
	}

	/*************************************************************************
	 * Get Rating {@link Rating} by ProductId and UserId
	 * 
	 * @return Rating
	 *************************************************************************/
	@Override
	public Rating getRatingByUserAndProduct(String pId, String uId) {
		return ratingRepo.getByProductIdAndUserId(pId, uId);
	}
	/*************************************************************************
	 * Update {@link Rating}
	 * 
	 * @param ob {@link Rating} object
	 * @return {@link Rating}
	 *************************************************************************/
	@Override
	public Rating update(Rating ob) {
		try {
			Rating existingRating = ratingRepo.findById(ob.getId()).orElse(null);
			BeanUtils.copyProperties(ob, existingRating);
			ratingRepo.save(existingRating);
			updateProductAvgRating(ob.getProductId());
			return ob;
			
		} catch (Exception e) {
			log.warn("Failed to update  Rating: ", e);
			return ob;
		}
	}
	
	public void updateProductAvgRating(String id){
		int avg=0;
		int c=0;
		Product existingProduct = productRepo.findById(id).orElse(null);
		List<Rating> rating =ratingRepo.getByProductId(id);
		for(Rating ob :rating) {
			avg=avg+ob.getRatingValue();
			c++;
		}
		avg=avg/c;
		existingProduct.setRating(avg);
		productRepo.save(existingProduct);
	}

}
