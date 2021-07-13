package com.jbianchini.meli.socialmeli.exception;

public class UserNotFounException extends ApplicationException{
    public UserNotFounException() {
        super("User not found.");
    }
}
