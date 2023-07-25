package com.example.ProductManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProductManagement.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

}
