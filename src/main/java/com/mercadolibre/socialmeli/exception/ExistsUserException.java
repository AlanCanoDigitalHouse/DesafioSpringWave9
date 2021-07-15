package com.mercadolibre.socialmeli.exception;

public class ExistsUserException extends UserException {
    public ExistsUserException() {
    }

    public ExistsUserException(String message) {
        super(message);
    }
}
