package com.example.desafiospring.exceptions;

import lombok.Getter;

@Getter
public class AlreadyFollowedException extends Exception {

    private String message;

    public AlreadyFollowedException(String pMessage) {
        this.message = pMessage;
    }
}
