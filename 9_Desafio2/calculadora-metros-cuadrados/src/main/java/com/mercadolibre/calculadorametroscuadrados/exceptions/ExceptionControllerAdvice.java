package com.mercadolibre.calculadorametroscuadrados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(LocationNotFoundException.class)
    public ErrorDTO locationNotFoundHandler(LocationNotFoundException ex){
        return new ErrorDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
