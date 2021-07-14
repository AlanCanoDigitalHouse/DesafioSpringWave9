package com.example.desafiospring.exceptions;

public class SellerException extends Exception {
    public static final String SELLER_NOT_EXISTS = "There's no seller for the specified ID: ";
    public static final String SELLER_ALREADY_FOLLOWED = "The seller is already followed by the user ID: ";
    public static final String FOLLOWER_NOT_FOUND = "Follower not found for seller ID: ";
    public static final String NO_SELLERS_FOUND = "No seller results";


    public SellerException(String message) {
        super(message);
    }

}
