package com.example.ProductManagement;




//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;




@SpringBootApplication
public class ProductManagementApplication {
	 private static ApplicationContext ctx =null;
	 
	
	public static void main(String[] args) throws Exception {
		//SpringApplication.run(ProductManagementApplication.class, args);
		ctx  = SpringApplication.run(ProductManagementApplication.class, args);
		
      
	}

	
	

}
