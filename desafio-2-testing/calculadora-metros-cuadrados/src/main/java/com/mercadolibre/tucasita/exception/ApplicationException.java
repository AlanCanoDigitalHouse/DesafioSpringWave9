package com.mercadolibre.tucasita.exception;

/**
 * ApplicationException is the main runtime exception of the application and it's used to throw exceptions when needed.
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }
}
