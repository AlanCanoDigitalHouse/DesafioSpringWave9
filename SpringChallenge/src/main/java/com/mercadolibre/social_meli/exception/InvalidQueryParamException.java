package com.mercadolibre.social_meli.exception;

public class InvalidQueryParamException extends RuntimeException {
    public InvalidQueryParamException(String message) {
        super(message);
    }
}
