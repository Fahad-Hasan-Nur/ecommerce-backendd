package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.SubCategoryDto;
import com.spring.ecommerce.model.SubCategory;
import com.spring.ecommerce.service.SubCategoryService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link SubCategory} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-31
 *************************************************************************/
@RestController
@RequestMapping("/api/subCategory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubCategoryController {

	private final SubCategoryService service;

	/*************************************************************************
	 * Create a new SubCategory
	 * 
	 * @param ob {@link SubCategory} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@PostMapping
	public SubCategory addProduct(@RequestBody SubCategory subCategory) {
		return service.create(subCategory);
	}

	/*************************************************************************
	 * Get all Product {@link SubCategory}
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<SubCategoryDto> getAllSubCategory() {
		return service.getAllSubCategory();
	}

	/*************************************************************************
	 * Get all active {@link SubCategory}
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	@GetMapping("/getAll/active")
	public List<SubCategoryDto> getAllActiveSubCategory() {
		return service.getAllActiveSubCategory();
	}

	/*************************************************************************
	 * Get all SubCategory {@link SubCategory} by Category Id
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	@GetMapping("/getAll/{id}")
	public List<SubCategoryDto> getAllSubCategoryByCategoryId(@PathVariable String id) {
		return service.getAllSubCategoryByCategoryId(id);
	}

	/*************************************************************************
	 * Get SubCategory {@link SubCategory} by Id
	 * 
	 * @return {@link SubCategory}
	 *************************************************************************/

	@GetMapping("/{id}")
	public SubCategory getSubCategoryById(@PathVariable String id) {
		return service.getSubCategoryById(id);
	}

	/*************************************************************************
	 * Update {@link SubCategory}
	 * 
	 * @param ob {@link SubCategory} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@PutMapping
	public SubCategory update( @RequestBody SubCategory ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link SubCategory}
	 * 
	 * @param ob {@link SubCategory} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
}
