package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
//Para errores de validacion en el body
public class ErrorMessage {

    private final Integer status;
    private final String error;
    private final Map<String, String> message;

    public ErrorMessage(Integer status, String error, Map<String, String> message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}