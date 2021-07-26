package com.mercadolibre.calculadorametroscuadrados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseOkDTO {
    private int statusCode;
    private String message;
}
