package com.db.exercise.service;

import com.db.exercise.entity.Product;
import com.db.exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Optional<Product> getById(String id) {
		return productRepository.findById(id);
	}

	@Override
	public Product addNewProduct(Product Product)  {
		return productRepository.save(Product);
	}
}