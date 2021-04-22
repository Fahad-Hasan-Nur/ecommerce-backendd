package com.spring.ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.model.DelivaryLocationInfo;
import com.spring.ecommerce.service.DelivaryLocationInfoService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link DelivaryLocationInfo} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-04-22
 *************************************************************************/
@RestController
@RequestMapping("/api/delivary/locationInfo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class DelivaryLocationInfoController {

	private final DelivaryLocationInfoService service;

	/*************************************************************************
	 * Create a new DelivaryLocationInfo
	 * 
	 * @param ob {@link DelivaryLocationInfo} object
	 * @return {@link DelivaryLocationInfo}
	 *************************************************************************/
	@PostMapping
	public DelivaryLocationInfo addDelivaryLocationInfo(@RequestBody DelivaryLocationInfo ob) {
		return service.create(ob);
	}
	
	/*************************************************************************
	 * Get DelivaryLocationInfo {@link DelivaryLocationInfo} by Id
	 * 
	 * @return {@link DelivaryLocationInfo}
	 *************************************************************************/

	@GetMapping("/{id}")
	public DelivaryLocationInfo getDelivaryLocationInfoById(@PathVariable String id) {
		return service.getById(id);
	}
}
