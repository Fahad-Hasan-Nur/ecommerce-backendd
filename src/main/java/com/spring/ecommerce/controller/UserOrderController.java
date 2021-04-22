package com.spring.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.UserOrder;
import com.spring.ecommerce.service.ProductService;
import com.spring.ecommerce.service.ProductServiceImpl;
import com.spring.ecommerce.service.UserOrderService;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link UserOrder} Controller
 * 
 * @author Fahad Hasan
 * @since 2021-04-22
 *************************************************************************/
@RestController
@RequestMapping("/api/user/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserOrderController {

	private final UserOrderService service;

	/*************************************************************************
	 * Create a new UserOrder
	 * 
	 * @param ob {@link UserOrder} object
	 * @return {@link UserOrder}
	 *************************************************************************/
	@PostMapping
	public UserOrder add(@RequestBody UserOrder ob) {
		return service.add(ob);
	}

	/*************************************************************************
	 * Get all UserOrder {@link UserOrder}
	 * 
	 * @return {@link List< UserOrder>}
	 *************************************************************************/
	@GetMapping("/getAll")
	public List<UserOrder> getAll() {
		return service.getAll();
	}

	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	@GetMapping("/{id}")
	public UserOrder getById(@PathVariable String id) {
		return service.getById(id);
	}
	
	/*************************************************************************
	 * Get UserOrder {@link UserOrder} by User Id
	 * 
	 * @return {@link UserOrder}
	 *************************************************************************/

	@GetMapping("/getByUserId/{id}")
	public List<UserOrder> getByUserId(@PathVariable String id) {
		return service.getByUserId(id);
	}
}
