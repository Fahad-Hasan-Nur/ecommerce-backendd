package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.model.Rating;
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

	/*************************************************************************
	 * Create a new Rating
	 * 
	 * @param ob {@link Rating} object
	 * @return {@link Rating}
	 *************************************************************************/
	@Override
	public Rating create(Rating ob) {
		try {
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
	public int getRatingByUserAndProduct(String pId, String uId) {
		return ratingRepo.getByProductIdAndUserId(pId, uId).getRatingValue();
	}

}
