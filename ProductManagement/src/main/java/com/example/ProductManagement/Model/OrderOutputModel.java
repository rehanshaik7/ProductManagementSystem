package com.example.ProductManagement.Model;

import java.time.LocalDate;

import com.example.ProductManagement.entity.Customer;
import com.example.ProductManagement.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderOutputModel {
	
	private int OrderId;
	private LocalDate orderedDate;
	private LocalDate estimatedDeliveryDate;
	private LocalDate deliveredDate;
	private double invoiceAmount;
	private int quantity;
	private String orderType;
	
	private int customerId;
	private int  productId;
	
	


}
