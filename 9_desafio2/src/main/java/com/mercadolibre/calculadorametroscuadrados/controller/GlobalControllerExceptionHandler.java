package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Collections;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(DistrictNotFoundException.class)
    public ResponseEntity handleInvalidUrlException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Message", "El Barrio no existe en la BD"));
    }

    /*@ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity handleInvalidPathException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Message", "Not Found Path District DB"));
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleNullPointerException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", ex.getBindingResult().getFieldError().getDefaultMessage()));
    }
}
