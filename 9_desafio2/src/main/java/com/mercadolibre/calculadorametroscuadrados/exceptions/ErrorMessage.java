package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessage {

    private Integer status;
    private Map<String, String> message;
    private String error;


    public ErrorMessage(Integer status, String error, Map<String, String> message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
