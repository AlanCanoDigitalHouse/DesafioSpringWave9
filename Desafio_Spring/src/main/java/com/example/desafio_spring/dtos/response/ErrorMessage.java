package com.example.desafio_spring.dtos.response;

import lombok.Data;

@Data
public class ErrorMessage {
    private Integer status;
    private String message;

    public ErrorMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
