package com.example.desafio2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)

public class ApiExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleAnyError(Exception exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
