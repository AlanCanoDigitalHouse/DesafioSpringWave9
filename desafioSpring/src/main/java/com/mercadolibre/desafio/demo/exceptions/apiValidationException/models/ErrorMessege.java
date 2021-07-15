package com.mercadolibre.desafio.demo.exceptions.apiValidationException.models;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessege {
    private Integer status;
    private String error;
    private Map<String, String> message;

    public ErrorMessege(Integer status, String error, Map<String, String> message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
