package com.meli.desafiospring.exceptions.custom;

public class PostDetailIsNullException extends RuntimeException {
    public PostDetailIsNullException(String message) {
        super(message);
    }
}
