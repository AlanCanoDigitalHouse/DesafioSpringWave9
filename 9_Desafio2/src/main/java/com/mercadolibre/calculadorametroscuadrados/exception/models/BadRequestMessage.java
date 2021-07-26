package com.mercadolibre.calculadorametroscuadrados.exception.models;

import lombok.Getter;

@Getter
public class BadRequestMessage {

    private final Integer statusCode;
    private final String message;

    public BadRequestMessage(Integer statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
