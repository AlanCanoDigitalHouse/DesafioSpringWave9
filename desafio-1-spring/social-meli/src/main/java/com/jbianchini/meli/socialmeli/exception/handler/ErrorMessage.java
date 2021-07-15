package com.jbianchini.meli.socialmeli.exception.handler;

import lombok.Getter;

import java.util.Map;

/**
 * An error message to be used in the controller.
 */
@Getter
public class ErrorMessage {

    private Integer status;
    private String error;
    private Map<String, String> message;

    public ErrorMessage(Integer status, String error, Map<String, String> message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
