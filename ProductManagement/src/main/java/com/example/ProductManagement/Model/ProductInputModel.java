package com.example.ProductManagement.Model;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductInputModel {
	
	private String name;
	private String description;
	private double price;
	private int stock;

}
