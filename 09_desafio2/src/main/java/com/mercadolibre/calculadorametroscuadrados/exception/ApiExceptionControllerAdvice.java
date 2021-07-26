package com.mercadolibre.calculadorametroscuadrados.exception;

import com.mercadolibre.calculadorametroscuadrados.dto.Response.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.Found.DistrictNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException",
                Objects.requireNonNull(
                        e.getBindingResult().getFieldError()).getDefaultMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(HttpMessageNotReadableException e) {
        ErrorDTO error = new ErrorDTO("HttpMessageNotReadableException", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DistrictNotFoundException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(DistrictNotFoundException e) {
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

}
