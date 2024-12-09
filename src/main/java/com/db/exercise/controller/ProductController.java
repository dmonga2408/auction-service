package com.db.exercise.controller;

import com.db.exercise.entity.Product;
import com.db.exercise.exception.ProductNotFoundException;
import com.db.exercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value="/{productId}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) {
		try {
			return productService.getById(productId)
					.map((product) -> new ResponseEntity<>(product, HttpStatus.OK))
					.orElseThrow(() -> new ProductNotFoundException(productId));
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<?> addNewProduct(@RequestBody Product Product) {
		return new ResponseEntity<>(productService.addNewProduct(Product), HttpStatus.CREATED);
	}
}
