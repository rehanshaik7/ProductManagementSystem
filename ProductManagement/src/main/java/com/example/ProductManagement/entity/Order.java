package com.example.ProductManagement.entity;


import java.sql.Date;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;

import com.example.ProductManagement.Model.CustomerOutputModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private LocalDate orderedDate;
	private LocalDate estimatedDeliveryDate;
	private LocalDate deliveredDate;
	private double invoiceAmount;
	private int quantity;
	private String orderType;
	
	@ManyToOne
	@JoinColumn(name="productId")
	
	private Product product;
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	

}
