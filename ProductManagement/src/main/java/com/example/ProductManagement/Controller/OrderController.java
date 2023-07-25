package com.example.ProductManagement.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductManagement.Exception.CustomerNotFoundException;
import com.example.ProductManagement.Exception.OrderException;
import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Model.OrderModel;
import com.example.ProductManagement.Model.OrderOutputModel;
import com.example.ProductManagement.entity.Order;
import com.example.ProductManagement.service.OrderService;
import com.example.ProductManagement.service.ProductService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrderController {

	  @Autowired
      OrderService orderService;
	  
	  private final Logger logger = LoggerFactory.getLogger(ProductService.class);
      
	 @PostMapping(value="/createorder")
	 public Order createOrder(@RequestBody OrderModel orderModel) throws ProductNotFoundException, OrderException, CustomerNotFoundException {
		 logger.info("Creating a new order");
		 Order order =null;
		 order = orderService.createOrder(orderModel);
		 logger.info("New order created");
		 return order;
	 }
      
	 @GetMapping(value="/getallorders")
	public List<OrderOutputModel> getALlOrders(){
		 logger.info("Getting all the orders");
		 
		 List<OrderOutputModel> orders= orderService.getOrders();
		 
		 logger.info("Retrieved all the orders");
		return orders;
		 
	}
	 	
	 @GetMapping(value="/searchorderbyid/{id}")
	 public OrderOutputModel searchById(@PathVariable int id) throws OrderException {
		 logger.info("Retrieve order by id{}",id);
		 OrderOutputModel order = orderService.searchById(id);
		 logger.info("Retrieving order by id");
		 return order;
	 }
	
}
