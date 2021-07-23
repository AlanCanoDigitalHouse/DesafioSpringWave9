package com.example.desafiotesting.exception;

import lombok.Getter;

@Getter
public class MessageError{

    private Integer status;
    private String error;

    public MessageError(Integer status, String error){
        this.status = status;
        this.error = error;
    }
}
