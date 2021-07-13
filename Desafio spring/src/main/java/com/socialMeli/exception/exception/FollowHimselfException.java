package com.socialMeli.exception.exception;

public class FollowHimselfException extends Exception {
    public FollowHimselfException(String userName) {
        super("User name " + userName + ", cannot follow to himself");
    }
}
