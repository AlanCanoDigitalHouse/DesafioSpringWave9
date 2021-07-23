package com.mercadolibre.tucasitatasaciones.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(BindingResult result) {
        HashMap<String, String> errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "ValidationException", null, errors);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(HttpMessageNotReadableException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName(), "the json object is not well formed", null);
    }

    @ExceptionHandler(value = {DistrictNotFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(DistrictNotFoundException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getClass().getSimpleName(), ex.getMessage(), null);
    }

}
