package com.meli.socialmeli.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ErrorMessage {
    private Integer status;
    private String error;
    private Map<String, String> message;

    public ErrorMessage(Integer status, String error){
        this.status = status;
        this.error = error;
        this.message = null;
    }

    public ErrorMessage(Integer status, String error, Map<String, String> message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
