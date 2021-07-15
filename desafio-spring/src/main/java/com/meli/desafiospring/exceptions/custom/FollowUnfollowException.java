package com.meli.desafiospring.exceptions.custom;

public class FollowUnfollowException extends RuntimeException {
    public FollowUnfollowException(String message) {
        super(message);
    }
}
