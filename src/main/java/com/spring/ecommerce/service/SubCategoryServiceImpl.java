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

import com.spring.ecommerce.dto.SubCategoryDto;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.SubCategory;
import com.spring.ecommerce.repository.CategoryRepo;
import com.spring.ecommerce.repository.SubCategoryRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link SubCategoryServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-31
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubCategoryServiceImpl implements SubCategoryService {

	private final SubCategoryRepo subCategoryRepo;
	private final CategoryRepo categoryRepo;

	/*************************************************************************
	 * Create a new SubCategory
	 * 
	 * @param ob {@link SubCategory} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public SubCategory create(SubCategory subCategory) {
		try {
			subCategory.setCategory(categoryRepo.findById(subCategory.getCategoryId()).orElse(null));
			return subCategoryRepo.save(subCategory);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			return subCategory;
		}
	}

	/*************************************************************************
	 * Get all SubCategory {@link SubCategory}
	 * 
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public List<SubCategoryDto> getAllSubCategory() {
		return subCategoryRepo.findAll().stream().map(this::getSubCategoryDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all Brand {@link SubCategory} by isActive flag
	 * 
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public List<SubCategoryDto> getAllActiveSubCategory() {
		return subCategoryRepo.findAllByIsActiveOrderByIdDesc(true).stream().map(this::getSubCategoryDtoFromEntity)
				.collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all SubCategory {@link SubCategory} by Category Id
	 * 
	 * @return {@link List< SubCategory>}
	 *************************************************************************/
	@Override
	public List<SubCategoryDto> getAllSubCategoryByCategoryId(String id) {

		return subCategoryRepo.findAllByCategory(categoryRepo.findById(id).orElse(null)).stream()
				.map(this::getSubCategoryDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get SubCategory {@link SubCategory} by Id
	 * 
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public SubCategory getSubCategoryById(String id) {
		return subCategoryRepo.findById(id).orElse(null);
	}

	/*************************************************************************
	 * Update {@link SubCategory}
	 * 
	 * @param ob {@link SubCategory} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public SubCategory update(SubCategory ob) {
		try {
			ob.setCategory(categoryRepo.findById(ob.getCategoryId()).orElse(null));
			SubCategory c = subCategoryRepo.findById(ob.getId()).orElse(null);
			BeanUtils.copyProperties(ob, c);
			return subCategoryRepo.save(c);
		} catch (Exception e) {
			log.warn("Failed to update  SubCategory: ", e);
			return ob;
		}
	}

	/*************************************************************************
	 * Delete {@link SubCategory}
	 * 
	 * @param ob {@link SubCategory} object
	 * @return {@link SubCategory}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> deleteById(String id) {
		try {
			subCategoryRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to update  SubCategory: ", e);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.BAD_REQUEST);
		}
	}

	public SubCategoryDto getSubCategoryDtoFromEntity(SubCategory ob) {
		ob.setCategoryId(ob.getCategory().getId());
		ob.setCategoryName(ob.getCategory().getName());
		SubCategoryDto obj = new SubCategoryDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

}
