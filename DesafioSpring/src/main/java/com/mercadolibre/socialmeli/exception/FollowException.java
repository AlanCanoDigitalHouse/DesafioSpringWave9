package com.mercadolibre.socialmeli.exception;

public class FollowException extends RuntimeException{
    public FollowException() { super(); }
    public FollowException(String message) {
        super(message);
    }

}
