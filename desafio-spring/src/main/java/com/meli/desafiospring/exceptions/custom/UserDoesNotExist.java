package com.meli.desafiospring.exceptions.custom;

public class UserDoesNotExist extends RuntimeException {

    public UserDoesNotExist(Integer userId) {
        super("User " + userId + " does not exist.");
    }
/*
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

 */
}
