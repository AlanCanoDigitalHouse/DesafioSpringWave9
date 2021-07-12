package com.example.desafiospring.exceptions;

public class ElementDoesntFindException extends RuntimeException{
    public ElementDoesntFindException(String message) {
        super(message);
    }
}
