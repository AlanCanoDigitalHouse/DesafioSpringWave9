package com.example.desafio1springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorMessage {
    private Integer status;
    private String error;
    private Map<String, String> message;

    public ErrorMessage(Integer status, String error) {
        this.status = status;
        this.error = error;
    }
}
