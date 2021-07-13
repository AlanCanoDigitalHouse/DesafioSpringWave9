package com.example.desafiospring.exceptions;

public class UserException extends Exception {
    public static final String USER_NOT_EXISTS = "There's no user for the specified ID: ";

    public UserException(String message) {
        super(message);
    }

}
