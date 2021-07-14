package com.mercadolibre.desafio1.exceptions;

public class UserNotExistException extends Exception{
    public UserNotExistException(String message) {
        super(message);
    }
}
