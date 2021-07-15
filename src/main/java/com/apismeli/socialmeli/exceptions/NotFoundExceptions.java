package com.apismeli.socialmeli.exceptions;

public class NotFoundExceptions extends Exception {
    public final String ERROR = "user with this ID was not found";

    public NotFoundExceptions() {
        super();
    }
}
