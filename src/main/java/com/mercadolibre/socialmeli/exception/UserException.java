package com.mercadolibre.socialmeli.exception;

public class UserException extends RuntimeException{
    public UserException() { super(); }

    public UserException(String message) {
        super(message);
    }
}
