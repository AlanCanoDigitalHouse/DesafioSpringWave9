package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private final Integer status;
    private final String error;
    private final Map<String, String> message;

}