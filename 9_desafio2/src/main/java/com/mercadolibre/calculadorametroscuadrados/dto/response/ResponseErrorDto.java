package com.mercadolibre.calculadorametroscuadrados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseErrorDto {
    private Integer status;
    private String error;
    private String message;
}
