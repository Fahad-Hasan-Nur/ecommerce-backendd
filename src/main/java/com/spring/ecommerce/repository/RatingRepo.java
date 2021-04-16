package com.spring.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Rating;

public interface RatingRepo extends JpaRepository<Rating, String> {

	List<Rating> findAllByProductId(String pId);
	Rating getByProductIdAndUserId(String pId, String uId);


}
