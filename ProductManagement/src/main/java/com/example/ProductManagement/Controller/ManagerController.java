package com.example.ProductManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductManagement.Exception.ManagerNotFoundException;
import com.example.ProductManagement.entity.Manager;
import com.example.ProductManagement.service.ManagerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@PostMapping("/registermanager")
	public Manager registerManager(@RequestBody Manager manager) {
		
		return managerService.registerManager(manager);
	}
	
	@GetMapping("/loginmanager/{managerId}/{password}")
	public Manager loginManager(@PathVariable String managerId,@PathVariable String password) throws ManagerNotFoundException {
	
		return managerService.loginManager(managerId, password);
		
	}
	
}
