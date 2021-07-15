package com.meli.desafiospring.exceptions.custom;

public class NoPostsAvailableException extends RuntimeException {

    public NoPostsAvailableException(Integer userId) {
        super("User " + userId + " does not follow other users or they have not posted anything in the last 2 weeks.");
    }
}
