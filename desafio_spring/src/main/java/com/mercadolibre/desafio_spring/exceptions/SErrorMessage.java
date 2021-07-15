package com.mercadolibre.desafio_spring.exceptions;

import lombok.Getter;

@Getter
public class SErrorMessage{

    private Integer status;
    private String error;

    public SErrorMessage(Integer status, String error){
        this.status = status;
        this.error = error;
    }
}