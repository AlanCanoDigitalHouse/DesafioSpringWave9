package com.example.desafio_spring.exceptions;

public class PostNotExistException extends Exception{
    public PostNotExistException(String message) {
        super(message);
    }
}
