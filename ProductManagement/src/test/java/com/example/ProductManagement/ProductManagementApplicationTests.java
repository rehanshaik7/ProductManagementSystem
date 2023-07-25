package com.example.ProductManagement;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.ProductManagement.Exception.ProductNotFoundException;

import com.example.ProductManagement.Model.ProductInputModel;
import com.example.ProductManagement.Model.ProductOutputModel;
import com.example.ProductManagement.entity.Product;
import com.example.ProductManagement.repository.ProductRepository;
import com.example.ProductManagement.service.ProductService;

class ProductManagementApplicationTests {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddProduct() {

		ProductInputModel inputProduct = new ProductInputModel();
		inputProduct.setName("Sample Product");
		inputProduct.setDescription("Sample description");
		inputProduct.setPrice(9.99);
		inputProduct.setStock(10);

		Product savedProduct = new Product();
		savedProduct.setId(23);
		when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

		Product addedProduct = productService.addProduct(inputProduct);

		verify(productRepository, times(1)).save(any(Product.class));

		assertEquals(savedProduct, addedProduct);

	}

	@Test
	public void testViewProductById() throws ProductNotFoundException {

		Product product = new Product();
		product.setId(1);
		product.setName("Sample Product");
		product.setDescription("Sample description");
		product.setPrice(9.99);
		product.setStock(10);

		when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));

		ProductOutputModel result = productService.viewProductById(1);
		verify(productRepository, times(1)).findById(1);

		assertNotNull(result);
		assertEquals(1, result.getId());
		assertEquals("Sample Product", result.getName());
		assertEquals("Sample description", result.getDescription());

		assertEquals(10, result.getStock());

	}

@Test
public void testViewProductById_InvalidId() {
   
    when(productRepository.findById(1)).thenReturn(java.util.Optional.empty());

    
    assertThrows(ProductNotFoundException.class, () -> {
        productService.viewProductById(1);
    });

  
    verify(productRepository, times(1)).findById(1);
}

	@Test
	public void testUpdateProduct_ValidId() throws ProductNotFoundException {

		Product existingProduct = new Product();
		existingProduct.setId(1);
		existingProduct.setName("Existing Product");
		existingProduct.setDescription("Existing description");
		existingProduct.setPrice(9.99);

		ProductInputModel updatedProductInput = new ProductInputModel();
		updatedProductInput.setName("Updated Product");
		updatedProductInput.setDescription("Updated description");
		updatedProductInput.setPrice(19.99);
		updatedProductInput.setStock(20);

		when(productRepository.getReferenceById(1)).thenReturn(existingProduct);

		when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

		ProductOutputModel result = productService.updateProduct(updatedProductInput, 1);

		verify(productRepository, times(1)).getReferenceById(1);

		assertEquals(updatedProductInput.getName(), existingProduct.getName());
		assertEquals(updatedProductInput.getDescription(), existingProduct.getDescription());
		assertEquals(updatedProductInput.getPrice(), existingProduct.getPrice(), 0.001);
		assertEquals(updatedProductInput.getStock(), existingProduct.getStock());

		verify(productRepository, times(1)).save(existingProduct);

		assertEquals(existingProduct.getId(), result.getId());
		assertEquals(existingProduct.getName(), result.getName());
		assertEquals(existingProduct.getDescription(), result.getDescription());
		assertEquals(existingProduct.getPrice(), result.getPrice(), 0.001);
		assertEquals(existingProduct.getStock(), result.getStock());
	}

@Test
public void testUpdateProduct_InvalidId() {
   
    when(productRepository.getReferenceById(999)).thenReturn(null);

   
    ProductInputModel updatedProductInput = new ProductInputModel();
    updatedProductInput.setName("Updated Product");
    updatedProductInput.setDescription("Updated description");
    updatedProductInput.setPrice(19.99);
    updatedProductInput.setStock(20);
    assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(updatedProductInput, 999));

   
    verify(productRepository, times(1)).getReferenceById(999);

  
    verify(productRepository, never()).save(any(Product.class));
}

	@Test
	public void testGetProducts() {

		List<Product> productList = new ArrayList<>();
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(9.99);
		product1.setStock(10);
		productList.add(product1);

		Product product2 = new Product();
		product2.setId(2);
		product2.setName("Product 2");
		product2.setDescription("Description 2");
		product2.setPrice(19.99);
		product2.setStock(5);
		productList.add(product2);

		when(productRepository.findAll()).thenReturn(productList);

		List<ProductOutputModel> result = productService.getProducts();

		verify(productRepository, times(1)).findAll();

		assertEquals(productList.size(), result.size());

		for (int i = 0; i < productList.size(); i++) {
			ProductOutputModel expectedModel = new ProductOutputModel();
			expectedModel.setId(productList.get(i).getId());
			expectedModel.setName(productList.get(i).getName());
			expectedModel.setDescription(productList.get(i).getDescription());
			expectedModel.setPrice(productList.get(i).getPrice());
			expectedModel.setStock(productList.get(i).getStock());

			ProductOutputModel actualModel = result.get(i);

			assertEquals(expectedModel.getId(), actualModel.getId());
			assertEquals(expectedModel.getName(), actualModel.getName());
			assertEquals(expectedModel.getDescription(), actualModel.getDescription());
			assertEquals(expectedModel.getPrice(), actualModel.getPrice());
			assertEquals(expectedModel.getStock(), actualModel.getStock());
		}
	}

	@Test
	public void testRemoveProductById_ValidId() throws ProductNotFoundException {

		Product product = new Product();
		product.setId(1);
		product.setName("Sample Product");
		product.setDescription("Sample description");
		product.setPrice(9.99);
		product.setStock(10);

		when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));

		assertDoesNotThrow(() -> productService.removeproductById(1));

		verify(productRepository, times(1)).findById(1);
		verify(productRepository, times(1)).deleteById(1);
	}

	@Test
    public void testRemoveProductById_InvalidId() {
   
        when(productRepository.findById(1)).thenReturn(java.util.Optional.empty());

       
        assertThrows(ProductNotFoundException.class, () -> {
            productService.removeproductById(1);
        });

        verify(productRepository, times(1)).findById(1);

        verify(productRepository, never()).deleteById(anyInt());
    }

	@Test
	public void testUpdateStock_ValidProductId() throws ProductNotFoundException {

		Product product = new Product();
		product.setId(1);
		product.setName("Sample Product");
		product.setDescription("Sample description");
		product.setPrice(9.99);
		product.setStock(10);

		when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));

		assertDoesNotThrow(() -> productService.updateStock(1, 20));

		verify(productRepository, times(1)).findById(1);
		verify(productRepository, times(1)).save(product);

		assertEquals(20, product.getStock());
	}

	@Test
    public void testUpdateStock_InvalidProductId() {
        
        when(productRepository.findById(1)).thenReturn(java.util.Optional.empty());

       
        assertThrows(ProductNotFoundException.class, () -> {
            productService.updateStock(1, 20);
        });

    
        verify(productRepository, times(1)).findById(1);

      
        verify(productRepository, never()).save(any());
    }

}
