package com.example.ProductManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProductManagement.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {

}
