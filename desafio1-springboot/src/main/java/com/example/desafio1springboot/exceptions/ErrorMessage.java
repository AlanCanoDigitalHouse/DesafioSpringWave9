package com.example.desafio1springboot.exceptions;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private Integer status;
    private String error;
    private Map<String, String> message;

    public ErrorMessage(Integer status, String error) {
        this.status = status;
        this.error = error;
    }
}
