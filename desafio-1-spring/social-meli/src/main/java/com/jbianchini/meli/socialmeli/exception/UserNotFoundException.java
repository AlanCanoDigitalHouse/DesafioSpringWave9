package com.jbianchini.meli.socialmeli.exception;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(String details) {
        super("User not found.", details);
    }
}
