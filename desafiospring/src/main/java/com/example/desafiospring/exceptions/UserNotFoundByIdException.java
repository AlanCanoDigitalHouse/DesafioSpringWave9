package com.example.desafiospring.exceptions;

public class UserNotFoundByIdException extends Exception{

    public final String ERROR = "User Not Found By Id";

    public UserNotFoundByIdException() {
        super();
    }

}
