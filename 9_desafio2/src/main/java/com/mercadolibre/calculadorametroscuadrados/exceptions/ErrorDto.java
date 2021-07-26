package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.List;

@Getter
public class ErrorDto {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorDto(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }


}