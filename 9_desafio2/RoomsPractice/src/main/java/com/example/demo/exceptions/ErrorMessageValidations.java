package com.example.demo.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessageValidations {

    private Integer status;
    private String error;
    private Map<String, String> message;

    public ErrorMessageValidations(Integer status, String error, Map<String, String> message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
