package com.spring.ecommerce.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.spring.ecommerce.dto.BrandDto;
import com.spring.ecommerce.model.Brand;



/*************************************************************************
* {@link  Brand} service class 
* 
* @author Fahad Hasan
* @since  2021-1-29
*************************************************************************/
public interface BrandService {

	/*************************************************************************
     * Create a new  Brand
     * @param ob {@link  Brand} object
     * @return {@link  Brand}
     *************************************************************************/
	 Brand create( Brand ob);
	
	  /*************************************************************************
     * Get all  {@link  Brand} 
     * @return {@link List< Brand>}
     *************************************************************************/
	List< BrandDto> getAllBrand();
	
	  /*************************************************************************
     * Get all  {@link  Brand} by isActive Flag
     * @return {@link List< Brand>}
     *************************************************************************/
	List<BrandDto> getAllActiveBrand();
	/*************************************************************************
	 * Get Brand {@link Brand} by Id
	 * 
	 * @return {@link Brand}
	 *************************************************************************/
	BrandDto getBrandById(String id);

	/*************************************************************************
	 * Update {@link Brand}
	 * 
	 * @param ob {@link Brand} object
	 * @param rs
	 * @return {@link Brand}
	 *************************************************************************/
	Brand update(Brand ob);

	/*************************************************************************
	 * Delete {@link Brand}
	 * 
	 * @param ob {@link Brand} object
	 * @param rs
	 * @return {@link Brand}
	 *************************************************************************/
	ResponseEntity<?> deleteById(String id );
}
