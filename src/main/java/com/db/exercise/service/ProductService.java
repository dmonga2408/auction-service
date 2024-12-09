package com.db.exercise.service;

import com.db.exercise.entity.Product;
import java.util.Optional;

public interface ProductService {
	
	Optional<Product> getById(String id);
	
	Product addNewProduct(Product product);

}
