package com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.models;

import lombok.Getter;

@Getter
public class NotFoundMessage {
    private final Integer status;
    private final String error;

    public NotFoundMessage(Integer status, String error) {
        this.status = status;
        this.error = error;
    }
}
