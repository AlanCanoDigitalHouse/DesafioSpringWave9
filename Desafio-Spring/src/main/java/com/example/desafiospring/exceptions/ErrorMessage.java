package com.example.desafiospring.exceptions;

import lombok.Getter;

import java.util.Map;


@Getter
public class ErrorMessage {

    private Integer status;
    private String message;


    public ErrorMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}




