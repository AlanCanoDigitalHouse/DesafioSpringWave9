package com.mercado_libre.bootcamp.desafio2.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
}
