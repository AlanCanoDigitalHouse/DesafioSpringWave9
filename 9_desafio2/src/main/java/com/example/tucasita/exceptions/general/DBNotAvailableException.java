package com.example.tucasita.exceptions.general;

public class DBNotAvailableException extends RuntimeException{

    public DBNotAvailableException(String message, Exception e) {
        super(message,e);
    }

}
