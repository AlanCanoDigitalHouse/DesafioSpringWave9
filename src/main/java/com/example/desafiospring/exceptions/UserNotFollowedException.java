package com.example.desafiospring.exceptions;

import lombok.Getter;

@Getter
public class UserNotFollowedException extends Exception {

    private String message;


    public UserNotFollowedException(String pMessage) {
        this.message = pMessage;
    }
}
