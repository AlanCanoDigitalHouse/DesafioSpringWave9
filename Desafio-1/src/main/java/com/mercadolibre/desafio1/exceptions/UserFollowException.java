package com.mercadolibre.desafio1.exceptions;

public class UserFollowException extends Exception{
    private String message;

    public UserFollowException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
