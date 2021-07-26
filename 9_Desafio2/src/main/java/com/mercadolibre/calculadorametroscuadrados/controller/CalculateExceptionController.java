package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CalculateExceptionController {

    /**
     * Metodo para manejar errores de input.
     * @param e Exception del tipo MethodArgumentNotValidException
     * @return Status code and Message de la exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        System.out.println(e.getFieldError());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Metodo para manejar errores de input.
     * @param e Exception del tipo HttpMessageNotReadableException
     * @return Status code and Message de la exception
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(HttpMessageNotReadableException e) {
        ErrorDTO error = new ErrorDTO("HttpMessageNotReadableException", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Metodo para manejar errores en caso de no encontrar el districto.
     * @param e Exception del tipo NotFoundLocation
     * @return Status code and Message de la exception
     */
    @ExceptionHandler(NotFoundLocation.class)
    protected ResponseEntity<ErrorDTO> notFoundLocation(NotFoundLocation e) {
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

}
