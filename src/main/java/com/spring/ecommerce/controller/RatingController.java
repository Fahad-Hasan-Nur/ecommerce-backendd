package com.spring.ecommerce.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.model.Rating;
import com.spring.ecommerce.service.RatingService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Rating} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-31
 *************************************************************************/
@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class RatingController {

	private final RatingService service;

	/*************************************************************************
	 * Create a new Rating
	 * 
	 * @param ob {@link Rating} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Rating}
	 *************************************************************************/
	@PostMapping
	public Rating addBrand(@RequestBody Rating rating) {
		return service.create(rating);
	}

	/*************************************************************************
	 * Get average Rating {@link Rating} by Product
	 * 
	 * @param Product Id
	 * @return Average Rating
	 ************************************************************************/
	@GetMapping("/getAverageRating")
	public int getAverageRatingByProduct(@RequestParam String id) {
		return service.getAverageRatingByProduct(id);
	}

	/*************************************************************************
	 * Get  Rating {@link Rating} by ProductId and UserId
	 * 
	 * @param Product Id
	 * @param User Id
	 * @return  Rating
	 *************************************************************************/
    @GetMapping("/getRating")
    public int getRating(@RequestParam String pId, @RequestParam String uId) {
    	return service.getRatingByUserAndProduct(pId, uId);
    }

}
