package com.example.ProductManagement;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.ProductManagement.Model.CustomerInputModel;
import com.example.ProductManagement.entity.Customer;
import com.example.ProductManagement.repository.CustomerRepository;
import com.example.ProductManagement.service.CustomerService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTests {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerService customerService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddCustomer() {
		CustomerInputModel inputCustomer = new CustomerInputModel();
		inputCustomer.setFirstName("John");
		inputCustomer.setLastName("Doe");
		inputCustomer.setPhoneNumber("1234567890");
		inputCustomer.setCity("New York");

		Customer savedCustomer = new Customer();
		savedCustomer.setId(42);
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		Customer addedCustomer = customerService.addCustomer(inputCustomer);

		
		verify(customerRepository, times(1)).save(any(Customer.class));

		assertEquals(savedCustomer, addedCustomer);
	}
}
