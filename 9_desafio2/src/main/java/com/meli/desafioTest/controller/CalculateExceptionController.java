package com.meli.desafioTest.controller;

import com.meli.desafioTest.Dtos.ErrorDTO;
import com.meli.desafioTest.exception.CalculateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CalculateExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
