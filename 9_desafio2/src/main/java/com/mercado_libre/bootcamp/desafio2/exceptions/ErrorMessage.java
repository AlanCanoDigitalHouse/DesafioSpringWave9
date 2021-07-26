package com.mercado_libre.bootcamp.desafio2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private final Integer status;
    private final String error;
    private final String message;
}
