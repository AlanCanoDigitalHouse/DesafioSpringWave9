package com.example.demo.Exceptions;

import lombok.Getter;

@Getter
public class ErrorMessage {

    private Integer status;
    private String error;

    public ErrorMessage(Integer status, String error) {
        this.status = status;
        this.error = error;
    }

}
