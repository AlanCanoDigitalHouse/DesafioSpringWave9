package com.mercado_libre.bootcamp.spring.desafio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorMessages {
    private Integer status;
    private String error;
    private Map<String, String> messages;
}
