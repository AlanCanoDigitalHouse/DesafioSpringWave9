package com.example.socialmeli.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessageStatus {

    private Integer status;
    private String message;


    public ErrorMessageStatus(Integer status, String message){
        this.status = status;
        this.message = message;
    }
}
