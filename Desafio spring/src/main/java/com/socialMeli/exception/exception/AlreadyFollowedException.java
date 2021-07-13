package com.socialMeli.exception.exception;

public class AlreadyFollowedException extends Exception {
    public AlreadyFollowedException(String fromName, String toName) {
        super("The user " + fromName + " already  follow " + toName + "");
    }
}
