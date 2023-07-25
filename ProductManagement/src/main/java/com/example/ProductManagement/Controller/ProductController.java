package com.example.ProductManagement.Controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Model.ProductInputModel;
import com.example.ProductManagement.Model.ProductOutputModel;
import com.example.ProductManagement.entity.Product;
import com.example.ProductManagement.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {
	

	@Autowired
	private ProductService service;
	
	private final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@PostMapping("/addproduct")
	public Product addProduct(@RequestBody ProductInputModel productInputModel) {
		logger.info("adding a product");
		Product product =service.addProduct(productInputModel);
		logger.info("added a product");
		return product;
	
	}


	
	@GetMapping("/getproducts")
	public List<ProductOutputModel> findAllProducts() {
		logger.info("Retrieving all products");
		List<ProductOutputModel> studentOutputModel= service.getProducts();
		logger.info("Retrieved {} products ",studentOutputModel.size());
		return studentOutputModel;
	}
	@GetMapping("/searchproductbyid/{id}")
	public ProductOutputModel searchProductById(@PathVariable int id) throws ProductNotFoundException {
		logger.info("Retrieving product by id:{}",id);
		ProductOutputModel productOutputModel =service.viewProductById(id);
		logger.info("Retrieving product by id:{}",id);
		return productOutputModel;
	}
	
	@PutMapping("/updateproduct/{id}")
	public ProductOutputModel updateProduct(@RequestBody ProductInputModel productInputModel,@PathVariable int id) throws ProductNotFoundException {
		logger.info("Updating product by id:{}",id);
		ProductOutputModel newProduct = service.updateProduct(productInputModel,id);
		logger.info("Product Updated");
		return newProduct;
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	public void deleteProductById(@PathVariable int id) throws ProductNotFoundException {
		logger.info("Delete product by id:{}",id);
		logger.info("product deleted");
		 service.removeproductById(id);
	}
	

	
}
