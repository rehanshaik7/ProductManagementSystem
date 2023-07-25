package com.example.ProductManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductManagement.Exception.CustomerNotFoundException;
import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Model.CustomerInputModel;
import com.example.ProductManagement.Model.CustomerOutputModel;
import com.example.ProductManagement.Model.ProductOutputModel;
import com.example.ProductManagement.entity.Customer;
import com.example.ProductManagement.entity.Product;
import com.example.ProductManagement.repository.CustomerRepository;

@Service
public class CustomerService {

	
	@Autowired
	CustomerRepository customerRepository;
	private final Logger logger = LoggerFactory.getLogger(ProductService.class);
	

	public Customer addCustomer(CustomerInputModel customerInputModel) {
		logger.info("addcustomer() method starts");
	Customer customer = new Customer();
	//ProductInputModel model = new ProductInputModel();
	//customer.setId(customerInputModel.getId());
    customer.setFirstName(customerInputModel.getFirstName());
	customer.setLastName(customerInputModel.getLastName());
	customer.setPhoneNumber(customerInputModel.getPhoneNumber());
	customer.setCity(customerInputModel.getCity());
	
	customer=customerRepository.save(customer);
	logger.info("addcustomer() method ends");
	return customer;
	
	}
	


	public CustomerOutputModel searchCustomerById(int id) throws CustomerNotFoundException {   //get
		
		logger.info(" search customer method starts()");
		Customer customer= customerRepository.findById(id).orElse(null);
		if(customer==null) 
			throw new CustomerNotFoundException("Customer not Found");
		//return customer;
		CustomerOutputModel model=new CustomerOutputModel();
		model.setId(customer.getId());
		model.setFirstName(customer.getFirstName());
		model.setLastName(customer.getLastName());
		model.setPhoneNumber(customer.getPhoneNumber());
		model.setCity(customer.getCity());
		logger.info("search customer method ends()");

	return model;
	
	

		
		
	}
//	
	public List<CustomerOutputModel> getCustomers(){
		logger.info("Get all customers method starts()");
		List<Customer> customerList= customerRepository.findAll();
		
		List<CustomerOutputModel> list= new ArrayList<>();
		
		for(Customer c :customerList) {
			CustomerOutputModel model=new CustomerOutputModel();
			model.setId(c.getId());
			model.setFirstName(c.getFirstName());
			model.setLastName(c.getLastName());
			model.setPhoneNumber(c.getPhoneNumber());
			model.setCity(c.getCity());
			list.add(model);
			
		}
		logger.info("Get all customers method ends()");
		return list;
	
	}
	

	
	
	
	//delete
	public void removeCustomerById(int id) throws CustomerNotFoundException {
		logger.info(" Remove customer by id method starts()");
		Customer customer= customerRepository.findById(id).orElse(null);
		if(customer==null) 
			throw new CustomerNotFoundException("Customer not Found");
			else 
			  customerRepository.deleteById(id);
		logger.info("Remove customer by id method ends()");
		
	}
	
	
	//update

	public  CustomerOutputModel updateCustomer(CustomerInputModel customerInputModel, int id) throws CustomerNotFoundException{  //update
		logger.info("Update customer method starts()");
		Customer existingCustomer = customerRepository.getReferenceById(id);
		existingCustomer.setFirstName(customerInputModel.getFirstName());
		existingCustomer.setLastName(customerInputModel.getLastName());
		existingCustomer.setPhoneNumber(customerInputModel.getPhoneNumber());
		existingCustomer.setCity(customerInputModel.getCity());
		
		Customer updatedCustomer=customerRepository.save(existingCustomer);
		
		CustomerOutputModel updatedCustomerModel= new CustomerOutputModel();
		updatedCustomerModel.setId(updatedCustomer.getId());
		updatedCustomerModel.setFirstName(updatedCustomer.getFirstName());
		updatedCustomerModel.setLastName(updatedCustomer.getLastName());
		updatedCustomerModel.setPhoneNumber(updatedCustomer.getPhoneNumber());
		updatedCustomerModel.setCity(updatedCustomer.getCity());
		
		
		logger.info("Update customer method ends()");
		
		return updatedCustomerModel;
	}
	
}
