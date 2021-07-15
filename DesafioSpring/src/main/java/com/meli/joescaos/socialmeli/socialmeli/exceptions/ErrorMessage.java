package com.meli.joescaos.socialmeli.socialmeli.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
