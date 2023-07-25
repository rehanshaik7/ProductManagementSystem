package com.example.ProductManagement.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductManagement.Exception.CustomerNotFoundException;
import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Model.CustomerInputModel;
import com.example.ProductManagement.Model.CustomerOutputModel;
import com.example.ProductManagement.Model.ProductOutputModel;
import com.example.ProductManagement.entity.Customer;
import com.example.ProductManagement.entity.Product;
import com.example.ProductManagement.service.CustomerService;
import com.example.ProductManagement.service.ProductService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	private final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@PostMapping("/addcustomer")
	public Customer addCustomer(@RequestBody CustomerInputModel customerInputModel) {
		logger.info("Adding a new customer");
		Customer customer= customerService.addCustomer(customerInputModel);
		logger.info("Added a customer");
		return customer;
	}
	
	@GetMapping("/getcustomers")
	public List<CustomerOutputModel> findAllCustomers() {	
		logger.info("Getting all the custoemrs");
		List<CustomerOutputModel> customers=customerService.getCustomers();
		logger.info("Retrieved all the customers");
		return customers;
	}
	@GetMapping("/searchcustomerbyid/{id}")
	public CustomerOutputModel searchCustomerById(@PathVariable int id) throws CustomerNotFoundException {
		logger.info("Getting a customer by id:{}",id);
		CustomerOutputModel customer = customerService.searchCustomerById(id);
		logger.info("Found the customer");
		return customer;
	}
	
	
	@PutMapping("/updatecustomer/{id}")
	public CustomerOutputModel updateCustomer(@RequestBody CustomerInputModel customerInputModel,@PathVariable int id) throws CustomerNotFoundException {
		logger.info("Updating a customer by id:{}",id);
		CustomerOutputModel newCustomer = customerService.updateCustomer(customerInputModel,id);
		logger.info("customer updated");
		return newCustomer;
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	public void deleteCustomerById(@PathVariable int id) throws CustomerNotFoundException {
		logger.info("Deleting a customer by id:{}",id);
		logger.info("Deleted a customer");
		 customerService.removeCustomerById(id);
	}

		
		
		
		

}
