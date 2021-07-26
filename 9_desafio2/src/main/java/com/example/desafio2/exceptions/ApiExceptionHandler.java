package com.example.desafio2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)

public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorListMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    private ErrorListMessage processField(List<FieldError> fieldErrors) {
        List<String> listErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            listErrors.add(fieldError.getDefaultMessage());
        }
        return new ErrorListMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", listErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleAnyError(Exception exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getLocalizedMessage());
    }
}
