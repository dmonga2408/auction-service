package com.db.exercise.exception;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String productId) {
		super(String.format("The product with ID %s was not found", productId));
	}
}
