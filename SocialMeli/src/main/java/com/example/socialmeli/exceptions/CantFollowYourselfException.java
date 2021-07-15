package com.example.socialmeli.exceptions;

import lombok.Getter;

@Getter
public class CantFollowYourselfException extends Exception{

    public CantFollowYourselfException(String message) {
        super(message);
    }
}

