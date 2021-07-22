package com.bootcamp.desafio2.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessage extends Exception {

    private Integer status;
    private String error;
    private Map<String, String> message2;

    public ErrorMessage(String error) {
        this.error = error;
    }

    public ErrorMessage(Integer status, String error, Map<String, String> message2) {
        this.status = status;
        this.error = error;
        this.message2 = message2;
    }
}
