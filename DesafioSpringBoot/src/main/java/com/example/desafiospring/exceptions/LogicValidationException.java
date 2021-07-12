package com.example.desafiospring.exceptions;

public class LogicValidationException extends RuntimeException{
    public LogicValidationException(String message) {
        super(message);
    }
}
