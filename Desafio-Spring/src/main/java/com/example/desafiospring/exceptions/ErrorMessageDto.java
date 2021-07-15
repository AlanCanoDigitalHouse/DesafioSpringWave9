package com.example.desafiospring.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessageDto {
    private Integer status;
    private String error;
    private Map<String, String> message;

    public ErrorMessageDto(Integer status, String error, Map<String, String> message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
