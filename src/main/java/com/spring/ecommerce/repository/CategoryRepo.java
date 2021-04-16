package com.spring.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Category;

public interface CategoryRepo extends JpaRepository<Category, String> {

	List<Category> findAllByIsActiveOrderByIdDesc(boolean isActive);
}
