package com.example.socialmeli.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMsg {
    private Integer status;
    private String error;
    private Map<String,String> msg;

    public ErrorMsg(Integer status, String error, Map<String, String> msg) {
        this.status = status;
        this.error = error;
        this.msg = msg;
    }
}