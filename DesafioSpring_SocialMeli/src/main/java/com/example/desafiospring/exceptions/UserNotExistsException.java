package com.example.desafiospring.exceptions;

import lombok.Getter;

@Getter
public class UserNotExistsException extends Exception {

    private String message;

    public UserNotExistsException(String pMessage) {
        this.message = pMessage;
    }
}
