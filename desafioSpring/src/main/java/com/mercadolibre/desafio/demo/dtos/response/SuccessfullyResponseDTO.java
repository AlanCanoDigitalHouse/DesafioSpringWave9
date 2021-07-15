package com.mercadolibre.desafio.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessfullyResponseDTO {
    private final Integer statusCode;
    private final String message;
}
