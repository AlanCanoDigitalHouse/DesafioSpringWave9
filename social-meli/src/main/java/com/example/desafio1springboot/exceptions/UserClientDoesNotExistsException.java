package com.example.desafio1springboot.exceptions;

public class UserClientDoesNotExistsException extends Exception{

    public final String ERROR = "This client does not exists in the database";

    public UserClientDoesNotExistsException() {
        super();
    }
}
