package com.example.desafiotesting.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private Integer status;
    private String error;
    private Map<String,String> message;
}
