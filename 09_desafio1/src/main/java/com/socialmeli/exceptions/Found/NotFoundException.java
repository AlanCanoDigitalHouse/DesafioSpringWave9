package com.socialmeli.exceptions.Found;

public class NotFoundException extends RuntimeException {

    public static String ERROR;

    public NotFoundException() {
        ERROR = "Resource Not Found ";
    }
}
