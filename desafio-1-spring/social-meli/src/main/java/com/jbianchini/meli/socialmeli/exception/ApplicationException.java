package com.jbianchini.meli.socialmeli.exception;

import lombok.Getter;

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
