package com.example.desafio_spring.exceptions;

public class UserNotExistException extends Exception{
    public UserNotExistException(String message) {
        super(message);
    }
}
