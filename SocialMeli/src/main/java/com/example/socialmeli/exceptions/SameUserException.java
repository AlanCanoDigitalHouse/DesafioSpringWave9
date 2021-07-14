package com.example.socialmeli.exceptions;

public class SameUserException extends Exception {

    public static final String ERROR = "Error, no puede seguir al mismo usuario";
    public static final String ERROR_UNFOLLOW = "Error, no puede dejar de seguir al si mismo";

    public SameUserException(String message) {
        super(message);
    }
}
