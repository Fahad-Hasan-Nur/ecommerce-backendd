package com.spring.ecommerce.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.spring.ecommerce.model.Image;

public interface ImageService {

	/*************************************************************************
	 * Upload a new Image
	 * 
	 * @param ob {@link Image} object
	 * @return {@link Image}
	 *************************************************************************/
	public Image uplaodImage(MultipartFile file) throws IOException;

	/*************************************************************************
	 * Get Image {@link Image} by name
	 * 
	 * @return {@link Image}
	 *************************************************************************/
	public Image getImage(String id);

}
