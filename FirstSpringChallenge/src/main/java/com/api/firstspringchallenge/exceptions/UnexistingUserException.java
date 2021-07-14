package com.api.firstspringchallenge.exceptions;

public class UnexistingUserException extends RuntimeException{
    public UnexistingUserException(String message){
        super(message);
    }
}
