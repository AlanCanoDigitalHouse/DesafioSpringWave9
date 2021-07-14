package com.example.desafiospring.exceptions;

public class ProductException extends Exception {

    public static final String PRODUCT_NOT_EXISTS = "Product not exists for ID: ";

    public ProductException(String message) {
        super(message);
    }
}
