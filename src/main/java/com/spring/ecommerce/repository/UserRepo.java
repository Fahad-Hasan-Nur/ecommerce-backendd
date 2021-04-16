package com.spring.ecommerce.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.dto.UserDto;
import com.spring.ecommerce.model.User;

public interface UserRepo extends JpaRepository<User, String> {

	User findByEmail(String email);

	User findByEmailIgnoreCase(String email);

	User findByEmailAndActiveAndType(String email,boolean active,String type);

	List<User> findAllByType(String type);

	User findByEmailAndActive(String email, boolean b);

	List<User> findAllByRoleAndActiveAndVerified(String string,boolean active,boolean verified);

	User findByEmailAndActiveAndVerified(String email, boolean b, boolean c);




}
