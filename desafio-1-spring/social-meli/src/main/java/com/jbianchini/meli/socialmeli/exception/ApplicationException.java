package com.jbianchini.meli.socialmeli.exception;

public class ApplicationException extends Exception {
    public ApplicationException() {
        super("An unexpected error occurred");
    }

    public ApplicationException(String message) {
        super(message);
    }
}
