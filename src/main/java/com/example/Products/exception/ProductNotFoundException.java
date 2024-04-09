package com.example.Products.exception;

public class ProductNotFoundException extends  RuntimeException {

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

}
