package com.mercadolibre.calculadorametroscuadrados.exceptions.apiValidationException.models;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessege {
    private final Integer status;
    private final String error;
    private final Map<String, String> message;

    public ErrorMessege(Integer status, String error, Map<String, String> message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
