package com.meli.bootcamp.exceptions;

import com.meli.bootcamp.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    /**
     * Exception throw when a field in the request couldn't pass the validations constrains
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ExceptionDTO badRequest(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(Objects.requireNonNull(ex.getFieldError()).getField(), ex.getFieldError().getDefaultMessage());
        return ExceptionDTO.builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getFieldError().getDefaultMessage())
                .details(errors)
                .build();}

    /**
     * Exception throw when a value  in the request doesn't match the expected type
     */
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ExceptionDTO badRequest(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getClass().getSimpleName(), "El payload contiene un tipo de datos no permitido");
        return ExceptionDTO.builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .message("El payload contiene un tipo de datos no permitido")
                .details(errors)
                .build();
    }

    /**
     * Exception throw when a district has not been found in the repository
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DistrictException.class})
    public ExceptionDTO badRequest(DistrictException ex) {
        Map<String, String> errors = new HashMap<>();
        return ExceptionDTO.builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .details(null)
                .build();}
}
