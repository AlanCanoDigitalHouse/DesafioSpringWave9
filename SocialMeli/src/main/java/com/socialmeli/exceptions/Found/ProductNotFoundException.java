package com.socialmeli.exceptions.Found;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException() {
        ERROR = "Product not found in Registers";
    }
}
