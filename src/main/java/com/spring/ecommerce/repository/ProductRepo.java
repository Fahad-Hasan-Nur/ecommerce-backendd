package com.spring.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.ecommerce.model.Brand;
import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.SubCategory;

public interface ProductRepo extends JpaRepository<Product,String>{

	List<Product> findByName(String name);

	List<Product> findAllByIsActiveOrderByIdDesc(boolean isActive);

	List<Product> findAllByCategory(Category ob);
	
	List<Product> findAllBySubCategory(SubCategory ob);

	List<Product> findByBrand(Brand ob);

	List<Product> findAllByBrand(Brand orElse);
	
	@Query(	"select c from Product c where c.name like %?1%")
    List<Product> filterByName(String name);


	//"SELECT * FROM ecommerce_final_project.ecommerce_product where name  LIKE '%name%'"
}
