package com.spring.ecommerce.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ecommerce.model.Image;
import com.spring.ecommerce.service.ImageService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link Image} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-02-09
 *************************************************************************/
@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {

	private final ImageService service;

	/*************************************************************************
	 * Upload a new Image
	 * 
	 * @param ob {@link Image} object
	 * @return {@link Image}
	 *************************************************************************/
	@PostMapping("/upload")
	public Image uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		return service.uplaodImage(file);
	}

	/*************************************************************************
	 * Get Image {@link Image} by id
	 * 
	 * @return {@link id}
	 *************************************************************************/
	@GetMapping(path = { "/get/{id}" })
	public Image getImage(@PathVariable("id") String id) throws IOException {
		return service.getImage(id);
	}
}
