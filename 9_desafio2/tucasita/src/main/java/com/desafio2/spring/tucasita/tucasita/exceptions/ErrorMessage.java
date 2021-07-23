package com.desafio2.spring.tucasita.tucasita.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private Integer status;
    private String error;
    private Map<String, String> message;
}
