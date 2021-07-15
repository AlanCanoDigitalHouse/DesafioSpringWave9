package com.mercadolibre.socialmeli.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessage {

    private final Integer status;
    private final String error;
    private final Map<String, String> message;

    public ErrorMessage(Integer status, String error, Map<String, String> message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}