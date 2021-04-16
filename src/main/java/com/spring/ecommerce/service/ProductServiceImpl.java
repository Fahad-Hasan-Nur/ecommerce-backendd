package com.spring.ecommerce.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.SubCategory;
import com.spring.ecommerce.model.ProductVariation;
import com.spring.ecommerce.repository.BrandRepo;
import com.spring.ecommerce.repository.CategoryRepo;
import com.spring.ecommerce.repository.ImageRepo;
import com.spring.ecommerce.repository.ProductRepo;
import com.spring.ecommerce.repository.SubCategoryRepo;
import com.spring.ecommerce.repository.VariationRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;


/*************************************************************************
 * {@link ProductServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;
	private final BrandRepo brandRepo;
	private final CategoryRepo categoryRepo;
	private final SubCategoryRepo typeRepo;
	private final ImageRepo imageRepo;
	private final VariationRepo variationRepo;


	/*************************************************************************
	 * Create a new Product
	 * 
	 * @param ob {@link Product} object
	 * @return {@link Product}
	 *************************************************************************/
	public Product create(Product product) {
		try {
			product.setBrand(brandRepo.findById(product.getBrandId()).orElse(null));
			product.setCategory(categoryRepo.findById(product.getCategoryId()).orElse(null));
			product.setSubCategory(typeRepo.findById(product.getSubCategoryId()).orElse(null));
			product.setImage(imageRepo.findById(product.getImageId()).orElse(null));
			return productRepo.save(product);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			return product;
		}
	}

	/*************************************************************************
	 * Get all Product {@link Product}
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	@Override
	public List<ProductDto> getAllProducts() {
		return productRepo.findAll().stream().map(this::getProjectDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get all Product {@link Product} by isActive flag
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	@Override
	public List<ProductDto> getAllActiveProducts() {
		return productRepo.findAllByIsActiveOrderByIdDesc(true).stream().map(this::getProjectDtoFromEntity)
				.collect(Collectors.toList());
	}

	/*************************************************************************
	 * Get Product {@link Product} by Id
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	public ProductDto getProductById(String id) {
		return productRepo.findById(id).map(this::getProjectDtoFromEntity).orElse(null);
	}
	
	/*************************************************************************
	 * Get Product {@link Product} by SubCategory Id
	 * 
	 * @return {@link Product}
	 *************************************************************************/
	public List<ProductDto> getProductBySubCategoryId(String id) {
		return productRepo.findAllBySubCategory(typeRepo.findById(id).orElse(null))
				.stream().map(this::getProjectDtoFromEntity).collect(Collectors.toList());
	}

	/*************************************************************************
	 * Update {@link Product}
	 * 
	 * @param ob {@link Product} object
	 * @return {@link Product}
	 *************************************************************************/
	@Override
	public Product update(Product product) {
		try {
			 Product existingProduct = productRepo.findById(product.getId()).orElse(null);
 
			product.setBrand(brandRepo.findById(product.getBrandId()).orElse(null));
			product.setCategory(categoryRepo.findById(product.getCategoryId()).orElse(null));
			product.setSubCategory(typeRepo.findById(product.getSubCategoryId()).orElse(null));
			product.setImage(imageRepo.findById(product.getImageId()).orElse(null));
			BeanUtils.copyProperties(product, existingProduct);
			return productRepo.save(existingProduct);
		} catch (Exception e) {
			log.warn("Failed to update  Product: ", e);
			return product;
		}
	}

	/*************************************************************************
	 * Delete {@link Product}
	 * 
	 * @param ob {@link Product} object
	 * @return {@link Product}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> deleteById(String id) {
		try {
			productRepo.deleteById(id);
			return new ResponseEntity<>("Deleted Succecfully.", HttpStatus.OK);
		} catch (Exception e) {
			log.warn("Failed to delete  Product: ", e);
			return new ResponseEntity<>("Unsccecfull.....!!!!!!!!!.", HttpStatus.BAD_REQUEST);
		}
	}
	/*************************************************************************
	 * Create a new Variation
	 * 
	 * @param ob {@link ProductVariation} object
	 * @return {@link ProductVariation}
	 *************************************************************************/
	public List<ProductVariation> createVariation(List<ProductVariation> variation) {
		try {
			for(ProductVariation ob: variation) {
				ob.setProduct(productRepo.findById(ob.getProductId()).orElse(null));			}
			return variationRepo.saveAll(variation);
		} catch (Exception e) {
			log.warn("Failed to create  Product: ", e);
			return variation;
		}
	}
	
	/*************************************************************************
	 * Get all Variation {@link ProductVariation} by product id
	 * 
	 * @return {@link ProductVariation}
	 *************************************************************************/
	@Override
	public List<ProductVariation> getVariationByProductId(String id) {
		return variationRepo.findAllByProduct(productRepo.findById(id).orElse(null))
				.stream().map(this::getVariation).collect(Collectors.toList());
	}
	
	/*************************************************************************
	 * Update {@link ProductVariation}
	 * 
	 * @param ob {@link ProductVariation} object
	 * @return {@link ProductVariation}
	 *************************************************************************/
	@Override
	public ProductVariation updateVariation(ProductVariation ob) {
		try {
			ProductVariation existingVariation = variationRepo.findById(ob.getId()).orElse(null);
			ob.setProduct(productRepo.findById(ob.getProductId()).orElse(null));
			BeanUtils.copyProperties(ob, existingVariation);
			return variationRepo.save(existingVariation);
		} catch (Exception e) {
			log.warn("Failed to update  Variation: ", e);
			return ob;
		}
	}

	public ProductDto getProjectDtoFromEntity(Product ob) {
		ob.setBrandId(ob.getBrand().getId());
		ob.setBrandName(ob.getBrand().getName());
		ob.setCategoryId(ob.getCategory().getId());
		ob.setCategoryName(ob.getCategory().getName());
		ob.setSubCategoryId(ob.getSubCategory().getId());
		ob.setSubCategoryName(ob.getSubCategory().getName());
		System.out.println(ob.getImage().getId());
		ob.setImageId(ob.getImage().getId());
		ob.setImageName(ob.getImage().getName());
		ProductDto obj = new ProductDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}
	
	public ProductVariation getVariation(ProductVariation ob) {
		ob.setProductId(ob.getProduct().getId());
		ob.setProductName(ob.getProduct().getName());
//		Variation obj = new Variation();
//		BeanUtils.copyProperties(ob, obj);
		return ob;
	}

}
