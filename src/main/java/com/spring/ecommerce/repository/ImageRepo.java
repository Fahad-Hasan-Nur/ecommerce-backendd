package com.spring.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.Image;

public interface ImageRepo extends JpaRepository<Image, String> {

	Optional<Image> findByName(String name);

}
