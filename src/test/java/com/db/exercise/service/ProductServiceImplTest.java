package com.db.exercise.service;

import com.db.exercise.entity.Product;
import com.db.exercise.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldCreateNewProduct(){
        Product product = new Product(1L, "testPro", 50.0);

        productService.addNewProduct(product);
        Optional<Product> productById = productService.getById("1");
        if (productById.isPresent()) {
            assertEquals("testPro", productById.get().getName());
            assertEquals(50.0, productById.get().getMinimumBidAmount());
        }

    }

}