package com.example.ProductManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductManagement.Exception.ManagerNotFoundException;
import com.example.ProductManagement.entity.Manager;
import com.example.ProductManagement.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	ManagerRepository managerRepository;
	
	
	public Manager registerManager(Manager manager) {
		
		return managerRepository.save(manager);
		
	}
	
	public Manager loginManager(String managerId,String password) throws ManagerNotFoundException {
		
		Manager manager = managerRepository.findById(managerId).orElse(null);
		if(manager==null) {
			throw new ManagerNotFoundException("Invalid ManagerId");
		}
		
		String pass=manager.getPassword();
		
		if(!(pass.equals(password))) {
           throw new ManagerNotFoundException("Invalid password");
}
		return manager;
		
	}
	
	
}
