package com.socialmeli.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessage{

    private String error;

    public ErrorMessage(String error){
        this.error = error;
    }
}
