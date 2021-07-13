package com.jbianchini.meli.socialmeli.exception;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
