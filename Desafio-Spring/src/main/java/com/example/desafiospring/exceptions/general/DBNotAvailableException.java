package com.example.desafiospring.exceptions.general;

public class DBNotAvailableException extends RuntimeException{

    public DBNotAvailableException(String message, Exception e) {
        super(message,e);
    }
}
