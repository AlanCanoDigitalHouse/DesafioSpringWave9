package com.jbianchini.meli.socialmeli.exception;

import lombok.Getter;

/**
 * ApplicationException is the main runtime exception of the application and it's used to throw exceptions when needed.
 */
@Getter
public class ApplicationException extends RuntimeException {
    private String details;

    public ApplicationException() {
        super("An unexpected error occurred");
        this.details = "Please contact the application support.";
    }

    public ApplicationException(String message, String details) {
        super(message);
        this.details = details;
    }
}
