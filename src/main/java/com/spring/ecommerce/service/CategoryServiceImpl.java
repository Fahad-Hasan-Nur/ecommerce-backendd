package com.spring.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.CategoryDto;
import com.spring.ecommerce.model.Brand;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.repository.CategoryRepo;
import com.spring.ecommerce.repository.ProductRepo;
import com.spring.ecommerce.repository.SubCategoryRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link CategoryServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepo categoryRepo;
	private final SubCategoryRepo subCategoryRepo;
	private final ProductRepo productRepo;

	/*************************************************************************
	 * Create a new Category
	 * 
	 * @param ob {@link Category} object
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public Category create(Category category) {
		try {
			return categoryRepo.save(category);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			return category;
		}
	}

	/*************************************************************************
	 * Get all Category {@link Category}
	 * 
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public List<CategoryDto> getAllCategory() {
		return categoryRepo.findAll().stream().map(this::getCategoryDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all Category {@link Category} by isActive flag
	 * 
	 * @return {@link Category}
	 *************************************************************************/
	@Override
	public List<CategoryDto> getAllActiveCategory() {
		return categoryRepo.findAllByIsActiveOrderByIdDesc(true).stream().map(this::getCategoryDtoFromEntity)
				.collect(Collectors.toList());
	}

	/*************************************************************************
     * Get Admin {@link  Category} by Id
     * 
     * @return {@link  Category}
     *************************************************************************/
	@Override
	public Category getCategoryById(String id) {
		return categoryRepo.findById(id).orElse(null);
	}
	/*************************************************************************
	 * Update {@link  Category}
	 * 
	 * @param ob {@link  Category} object
	 * @return {@link  Category}
	 *************************************************************************/
	@Override
	public  Category update(Category ob) {
		try {
			Category c = categoryRepo.findById(ob.getId()).orElse(null);
			BeanUtils.copyProperties(ob, c);
			return categoryRepo.save(c);
		} catch (Exception e) {
			log.warn("Failed to update  Product: ", e);
			return ob;
		}
	}
	
	/*************************************************************************
	 * Delete {@link  Category}
	 * 
	 * @param ob {@link  Category} object
	 * @return {@link  Category}
	 *************************************************************************/
	@Override
	public  ResponseEntity<?> deleteById(String id) {
		try {
			Category ob=categoryRepo.findById(id).orElse(null);
			subCategoryRepo.deleteAll(subCategoryRepo.findAllByCategory(ob));
			productRepo.deleteAll(productRepo.findAllByCategory(ob));
			categoryRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to update  Category: ", e);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.BAD_REQUEST);
		}
	}
	public CategoryDto getCategoryDtoFromEntity(Category ob) {
		CategoryDto obj = new CategoryDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
