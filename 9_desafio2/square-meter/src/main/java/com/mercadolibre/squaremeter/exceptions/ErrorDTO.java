package com.mercadolibre.squaremeter.exceptions;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorDTO {
    private Integer status;
    private String error;
    private Map<String, String> message;


    public ErrorDTO(Integer status, String error, Map<String, String> message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
