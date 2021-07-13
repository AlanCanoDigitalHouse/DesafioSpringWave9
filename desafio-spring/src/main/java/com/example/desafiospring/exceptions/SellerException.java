package com.example.desafiospring.exceptions;

public class SellerException extends Exception {
    public static final String SELLER_NOT_EXISTS = "There's no seller for the specified ID: ";
    public static final String SELLER_ALREADY_FOLLOWED = "The seller is already followed by the user ID: ";


    public SellerException(String message) {
        super(message);
    }

}
