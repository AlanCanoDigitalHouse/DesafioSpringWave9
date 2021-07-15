package com.jbianchini.meli.socialmeli.exception;

public class ValidationException extends ApplicationException {
    public ValidationException(String details) {
        super("There was an error validating some arguments.", details);
    }
}
