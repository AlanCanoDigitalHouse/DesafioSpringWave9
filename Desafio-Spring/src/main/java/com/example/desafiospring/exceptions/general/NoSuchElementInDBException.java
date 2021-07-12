package com.example.desafiospring.exceptions.general;

public class NoSuchElementInDBException extends RuntimeException{

    public NoSuchElementInDBException(String message) {
        super(message);
    }
}
