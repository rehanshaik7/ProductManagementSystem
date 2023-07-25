package com.example.ProductManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductManagement.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}
