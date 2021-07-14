package com.example.desafiospring.exceptions;

public class PostException extends Exception {

    public static final String POST_NOT_EXISTS = "Post not exists for ID: ";
    public static final String NO_POSTS_FOUND = "No posts found in database";
    public static final String POST_HAS_NOT_PRODUCTS = "Post doesn't has products";

    public PostException(String message) {
        super(message);
    }
}
