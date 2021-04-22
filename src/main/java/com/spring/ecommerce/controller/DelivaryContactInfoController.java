package com.spring.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.model.DelivaryContactInfo;
import com.spring.ecommerce.model.DelivaryLocationInfo;
import com.spring.ecommerce.service.DelivaryContactInfoService;
import com.spring.ecommerce.service.DelivaryLocationInfoService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link DelivaryContactInfo} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-04-22
 *************************************************************************/
@RestController
@RequestMapping("/api/delivary/contactInfo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class DelivaryContactInfoController {

	private final DelivaryContactInfoService service;

	/*************************************************************************
	 * Create a new DelivaryContactInfo
	 * 
	 * @param ob {@link DelivaryContactInfo} object
	 * @return {@link DelivaryContactInfo}
	 *************************************************************************/
	@PostMapping
	public DelivaryContactInfo addDelivaryContactInfo(@RequestBody DelivaryContactInfo ob) {
		return service.create(ob);
	}
	
	/*************************************************************************
	 * Get DelivaryContactInfo {@link DelivaryContactInfo} by Id
	 * 
	 * @return {@link DelivaryContactInfo}
	 *************************************************************************/

	@GetMapping("/{id}")
	public DelivaryContactInfo getDelivaryContactInfoById(@PathVariable String id) {
		return service.getById(id);
	}
}
