package com.example.ProductManagement.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.ProductManagement.Exception.CustomerNotFoundException;
import com.example.ProductManagement.Exception.OrderException;
import com.example.ProductManagement.Exception.ProductNotFoundException;
import com.example.ProductManagement.Model.ApiResponseError;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<ApiResponseError> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {
		ApiResponseError obj = new ApiResponseError();
		obj.setMessage(ex.getMessage());
		obj.setStatusCode("404");
		//obj.setUrl(request.getContextPath());
		obj.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);

	}

@ExceptionHandler(value = CustomerNotFoundException.class)
public ResponseEntity<ApiResponseError> handleCustomerNotFoundException(CustomerNotFoundException ex, 
		WebRequest request){
         ApiResponseError obj = new ApiResponseError();
        obj.setMessage(ex.getMessage());
        obj.setStatusCode("404");
        obj.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(value = OrderException.class)
public ResponseEntity<ApiResponseError> handleOrderException(OrderException ex, 
		WebRequest request){
         ApiResponseError obj = new ApiResponseError();
        obj.setMessage(ex.getMessage());
        obj.setStatusCode("404");
       // obj.setUrl(request.getContextPath());
        obj.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
}
}
