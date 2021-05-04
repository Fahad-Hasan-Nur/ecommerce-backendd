package com.spring.ecommerce.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.User;
import com.spring.ecommerce.model.UserOrder;


public interface UserOrderRepo extends JpaRepository<UserOrder, String>{

	List<UserOrder> findAllByUserOrderByCreatedAtDesc(User findById);

	List<UserOrder> findAllByStatus(String status);

}
