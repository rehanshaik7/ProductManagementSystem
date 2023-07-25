package com.example.ProductManagement.service;




import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Model.ProductInputModel;
import com.example.ProductManagement.Model.ProductOutputModel;
import com.example.ProductManagement.entity.Product;
import com.example.ProductManagement.repository.ProductRepository;




@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	private final Logger logger = LoggerFactory.getLogger(ProductService.class);

	
	public Product addProduct(ProductInputModel productInputModel) {
		logger.info("addproduct() method starts");
		
		Product product = new Product();
		//ProductInputModel model = new ProductInputModel();
		product.setName(productInputModel.getName());
		product.setDescription(productInputModel.getDescription());
		product.setPrice(productInputModel.getPrice());
		product.setStock(productInputModel.getStock());
		
		product=productRepository.save(product);
		logger.info("addproduct() method ends");
		return product;
		
		
	
	}
	


	
	
	public ProductOutputModel viewProductById(int id) throws ProductNotFoundException {                //get
		logger.info("viewProductById() method starts");
		Product product= productRepository.findById(id).orElse(null);
		if(product==null) 
			throw new ProductNotFoundException("Product not Found");
				ProductOutputModel model=new ProductOutputModel();
				model.setId(product.getId());
				model.setName(product.getName());
				model.setDescription(product.getDescription());
				model.setPrice(product.getPrice());
				model.setStock(product.getStock());
				
				logger.info("viewProductById() method ends");
			return model;
		
		
	}
//	
	public List<ProductOutputModel> getProducts(){
		logger.info("getProducts() method starts");
	
		List<Product> productList= productRepository.findAll();
		logger.info("products{}",productList.size());
		List<ProductOutputModel> list= new ArrayList<>();
		
		for(Product p :productList) {
			ProductOutputModel model=new ProductOutputModel();
			model.setDescription(p.getDescription());
			model.setName(p.getName());
			model.setId(p.getId());
			model.setPrice(p.getPrice());
			model.setStock(p.getStock());
			list.add(model);
			
		}
		logger.info("getProducts() method ends");
		return list;
	
	}
	

	
	
	
	//delete
	public void removeproductById(int id) throws ProductNotFoundException {
		logger.info("removeProductById() method starts");
		Product product= productRepository.findById(id).orElse(null);
		if(product==null) 
			throw new ProductNotFoundException("Product not Found");
			else 
				productRepository.deleteById(id);
		logger.info("removeProductById() method ends");
		
	}
	
	//update

	public  ProductOutputModel updateProduct(ProductInputModel productInputModel, int id) throws ProductNotFoundException{  //update
		logger.info("updateProduct() method starts");
		Product existingProduct = productRepository.getReferenceById(id);
		if(existingProduct==null) {
			throw new ProductNotFoundException("Product Not Found");
		}
		existingProduct.setName(productInputModel.getName());
		existingProduct.setDescription(productInputModel.getDescription());
		existingProduct.setPrice(productInputModel.getPrice());
		existingProduct.setStock(productInputModel.getStock());
		
		Product updatedProduct=productRepository.save(existingProduct);
		
		ProductOutputModel updatedOutputModel= new ProductOutputModel();
		updatedOutputModel.setId(updatedProduct.getId());
		updatedOutputModel.setName(updatedProduct.getName());
		updatedOutputModel.setDescription(updatedProduct.getDescription());
		updatedOutputModel.setPrice(updatedProduct.getPrice());
		updatedOutputModel.setStock(updatedProduct.getStock());
		
		
		
		logger.info("updateProduct() method ends");
		return updatedOutputModel;
		
		
	}

	public void updateStock(int productId, int updatedStock) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		logger.info("updateStock() method starts");
		Product product= productRepository.findById(productId).orElse(null);
		if(product==null) 
			throw new ProductNotFoundException("Product not Found");
		product.setStock(updatedStock);
		productRepository.save(product);
		logger.info("updateStock() method ends");
	}
	
	
	
	
	

}
