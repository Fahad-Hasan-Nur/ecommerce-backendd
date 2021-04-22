package com.spring.ecommerce.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.UserCartProduct;

public interface UserCartProductRepo extends JpaRepository<UserCartProduct, String> {

	List<UserCartProduct> findAllByUserOrderByIdDesc(User orElse);

	List<UserCartProduct> findAllByUserAndStatus(User orElse, String string);

	List<UserCartProduct> findAllByOrderId(String id);

}
