package com.mercado_libre.bootcamp.spring.desafio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private Integer status;
    private String error;
    private String message;
}
