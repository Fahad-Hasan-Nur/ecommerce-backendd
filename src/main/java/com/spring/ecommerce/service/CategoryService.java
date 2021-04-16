package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.dto.CategoryDto;
import com.spring.ecommerce.model.Category;



/*************************************************************************
* {@link Category} service class 
* 
* @author Fahad Hasan
* @since  2021-1-29
*************************************************************************/
public interface CategoryService {

	/*************************************************************************
     * Create a new Category
     * @param ob {@link Category} object
     * @return {@link Category}
     *************************************************************************/
	Category create(Category ob);
	
	  /*************************************************************************
     * Get all  {@link Category} 
     * @return {@link List<Category>}
     *************************************************************************/
	List<CategoryDto> getAllCategory();
	
	  /*************************************************************************
     * Get all  {@link Category} by isActive Flag
     * @return {@link List<Category>}
     *************************************************************************/
	List<CategoryDto> getAllActiveCategory();
	
	/*************************************************************************
	 * Get Category {@link Category} by Id
	 * 
	 * @return {@link Category}
	 *************************************************************************/
	Category getCategoryById(String id);

	/*************************************************************************
	 * Update {@link Category}
	 * 
	 * @param ob {@link Category} object
	 * @param rs
	 * @return {@link Category}
	 *************************************************************************/
	Category update(Category ob);

	/*************************************************************************
	 * Delete {@link Category}
	 * 
	 * @param ob {@link Category} object
	 * @param rs
	 * @return {@link Category}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id );
}
