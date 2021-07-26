package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessageException {

    private Integer status;
    private String error;

    public ErrorMessageException(Integer status, String error){
        this.status = status;
        this.error = error;
    }

}
