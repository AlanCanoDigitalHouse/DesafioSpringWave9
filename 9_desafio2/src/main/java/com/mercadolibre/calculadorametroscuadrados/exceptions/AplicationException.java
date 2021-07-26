package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.Getter;

@Getter
public class AplicationException extends Exception {

    protected String data;

    public AplicationException() {
        data = "Error no manejado";
    }
}