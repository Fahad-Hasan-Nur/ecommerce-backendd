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

import com.spring.ecommerce.dto.BrandDto;
import com.spring.ecommerce.model.Brand;
import com.spring.ecommerce.service.BrandService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Brand} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-01-31
 *************************************************************************/
@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class BrandController {

	private final BrandService service;

	/*************************************************************************
	 * Create a new Brand
	 * 
	 * @param ob {@link Brand} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link Brand}
	 *************************************************************************/
	@PostMapping
	public Brand addBrand(@RequestBody Brand brand) {
		return service.create(brand);
	}

	/*************************************************************************
	 * Get all Brand {@link Brand}
	 * 
	 * @return {@link List< Brand>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<BrandDto> getAllBrand() {
		return service.getAllBrand();
	}

	/*************************************************************************
	 * Get all active {@link Brand}
	 * 
	 * @return {@link List< Brand>}
	 *************************************************************************/
	@GetMapping("/getAll/active")
	public List<BrandDto> getAllActiveBrand() {
		return service.getAllActiveBrand();
	}

	/*************************************************************************
	 * Get Brand {@link Brand} by Id
	 * 
	 * @return {@link Brand}
	 *************************************************************************/

	@GetMapping("/{id}")
	public BrandDto getBrandById(@PathVariable String id) {
		return service.getBrandById(id);
	}

	/*************************************************************************
	 * Update {@link Brand}
	 * 
	 * @param ob {@link Brand} object
	 * @return {@link Brand}
	 *************************************************************************/
	@PutMapping
	public Brand update(@RequestBody Brand ob) {
		return service.update(ob);
	}

	/*************************************************************************
	 * Delete {@link Brand}
	 * 
	 * @param ob {@link Brand} object
	 * @return {@link Brand}
	 *************************************************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return service.deleteById(id);
	}
}
