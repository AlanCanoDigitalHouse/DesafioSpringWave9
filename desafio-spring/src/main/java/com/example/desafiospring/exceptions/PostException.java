package com.example.desafiospring.exceptions;

public class PostException extends Exception {

    public static final String POST_NOT_EXISTS = "Post not exists for ID: ";
    public static final String MISSING_HAS_PROMO_FIELD = "HasPromo is null";
    public static final String MISSING_DISCOUNT_FIELD = "Discount is null";

    public PostException(String message) {
        super(message);
    }
}
