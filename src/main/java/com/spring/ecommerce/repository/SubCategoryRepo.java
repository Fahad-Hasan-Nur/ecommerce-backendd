package com.spring.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory, String>{

	List<SubCategory> findAllByIsActiveOrderByIdDesc(boolean isActive);

	List<SubCategory> findAllByCategory(Category category);
}
