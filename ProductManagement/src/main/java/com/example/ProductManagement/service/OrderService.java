package com.example.ProductManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductManagement.Exception.CustomerNotFoundException;
import com.example.ProductManagement.Exception.OrderException;
import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Model.CustomerOutputModel;
import com.example.ProductManagement.Model.OrderModel;
import com.example.ProductManagement.Model.OrderOutputModel;
import com.example.ProductManagement.Model.ProductOutputModel;
import com.example.ProductManagement.entity.Customer;
import com.example.ProductManagement.entity.Order;
import com.example.ProductManagement.entity.Product;
import com.example.ProductManagement.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepo;
	
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	private final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Transactional
	public Order createOrder(OrderModel orderModel) throws ProductNotFoundException, OrderException, CustomerNotFoundException{
		logger.info("create order method starts()");
		ProductOutputModel product =productService.viewProductById(orderModel.getProductId());
		CustomerOutputModel customer =customerService.searchCustomerById(orderModel.getCustomerId());
		
		if(orderModel.getOrderType().equalsIgnoreCase("sale")) {
			if(product.getStock()-orderModel.getQuantity()<0) {
				throw new OrderException("Stock is insufficient");
	
			}
			Order order = new Order();
			order.setInvoiceAmount(orderModel.getQuantity()*product.getPrice());
			Customer c= new Customer();
			c.setId(customer.getId());
			c.setFirstName(customer.getFirstName());
			c.setLastName(customer.getLastName());
			c.setPhoneNumber(customer.getPhoneNumber());
			c.setCity(customer.getCity());
			order.setCustomer(c);
			Product p = new Product();
			p.setId(product.getId());
			p.setName(product.getName());
			p.setDescription(product.getDescription());
			p.setPrice(product.getPrice());
			p.setStock(product.getStock());
			
			order.setProduct(p);
			LocalDate orderedDate=LocalDate.now();
			LocalDate estimatedDeliverydate=orderedDate.plusDays(4);
			order.setQuantity(orderModel.getQuantity());
			order.setOrderedDate(orderedDate);
			order.setOrderType(orderModel.getOrderType());
			order.setDeliveredDate(estimatedDeliverydate);
			order.setEstimatedDeliveryDate(estimatedDeliverydate);
			order=orderRepo.save(order);
			
			productService.updateStock(orderModel.getProductId(),product.getStock()-orderModel.getQuantity());
			return order;
		}
		else {
			Order order = new Order();
			order.setInvoiceAmount(orderModel.getQuantity()*product.getPrice());
			Customer c= new Customer();
			c.setId(customer.getId());
			c.setFirstName(customer.getCity());
			c.setLastName(customer.getLastName());
			c.setPhoneNumber(customer.getPhoneNumber());
			c.setCity(customer.getCity());
			order.setCustomer(c);
			Product p = new Product();
			p.setId(product.getId());
			p.setName(product.getName());
			p.setDescription(product.getDescription());
			p.setPrice(product.getPrice());
			p.setStock(product.getStock());
			order.setProduct(p);
			LocalDate orderedDate=LocalDate.now();
			LocalDate estimatedDeliverydate=orderedDate.plusDays(4);
			order.setQuantity(orderModel.getQuantity());
			order.setOrderedDate(orderedDate);
			order.setOrderType(orderModel.getOrderType());
			order.setDeliveredDate(estimatedDeliverydate);
			order.setEstimatedDeliveryDate(estimatedDeliverydate);
			order=orderRepo.save(order);
			
			productService.updateStock(orderModel.getProductId(),product.getStock()+orderModel.getQuantity());
			logger.info("create order method ends()");
			return order;
			
		}
	}

		
		public List<OrderOutputModel> getOrders(){
			logger.info("Get all orders method starts()");
			List<Order> orderList= orderRepo.findAll();
			
			List<OrderOutputModel> list= new ArrayList<>();
			
			for(Order o :orderList) {
				OrderOutputModel model=new OrderOutputModel();
				model.setOrderId(o.getOrderId());
				model.setInvoiceAmount(o.getInvoiceAmount());
				model.setCustomerId(o.getCustomer().getId());
				model.setProductId(o.getProduct().getId());
			    model.setQuantity(o.getQuantity());
				model.setOrderedDate(o.getOrderedDate());
				model.setOrderType(o.getOrderType());
				model.setDeliveredDate(o.getDeliveredDate());
				model.setEstimatedDeliveryDate(o.getEstimatedDeliveryDate());
				
				list.add(model);
				
			}
			logger.info("Get all orders method ends()");
			
              return list;
			
		//	return orderRepo.findAll();
		
		}
		
		public OrderOutputModel searchById(int id) throws OrderException{
			
			logger.info("Search order by id method starts()");
			
			Order order= orderRepo.findById(id).orElse(null);
			if(order==null) 
				throw new OrderException("Order not Found");
					OrderOutputModel model=new OrderOutputModel();
					model.setOrderId(order.getOrderId());
					model.setInvoiceAmount(order.getInvoiceAmount());
					model.setCustomerId(order.getCustomer().getId());
				    model.setProductId(order.getProduct().getId());
					model.setQuantity(order.getQuantity());
					model.setOrderedDate(order.getOrderedDate());
					model.setOrderType(order.getOrderType());
					model.setDeliveredDate(order.getDeliveredDate());
					model.setEstimatedDeliveryDate(order.getEstimatedDeliveryDate());
					
			
	            logger.info("search order by id method ends()");
		return model;
				
		}
		
		
		
	
	
	}


