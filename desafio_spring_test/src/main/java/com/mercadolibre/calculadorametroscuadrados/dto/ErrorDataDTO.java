package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorDataDTO {
    private final Integer status;
    private final String error;
    private final Map<String, String> message;

    public ErrorDataDTO(Integer status, String error, Map<String, String> message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
