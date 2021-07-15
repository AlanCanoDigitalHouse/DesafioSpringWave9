package com.example.desafiospring.exceptions;

import lombok.Getter;


@Getter
public class ErrorValidationMessage {
    private Integer status;
    private String message;
    private String error;


    public ErrorValidationMessage(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
