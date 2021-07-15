package com.example.desafiospring.exceptions;

public class UserNotExistException extends Exception{

    public final String ERROR_USER = "User does not exist";

    public UserNotExistException() {
        super();
    }
}
