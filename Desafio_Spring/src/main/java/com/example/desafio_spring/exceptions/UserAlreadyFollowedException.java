package com.example.desafio_spring.exceptions;

public class UserAlreadyFollowedException extends Exception{
    public UserAlreadyFollowedException(String message) {
        super(message);
    }
}
