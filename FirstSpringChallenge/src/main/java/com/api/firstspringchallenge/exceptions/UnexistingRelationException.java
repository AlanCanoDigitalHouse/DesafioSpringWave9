package com.api.firstspringchallenge.exceptions;

public class UnexistingRelationException extends RuntimeException{
    public UnexistingRelationException(String message){
        super(message);
    }
}
