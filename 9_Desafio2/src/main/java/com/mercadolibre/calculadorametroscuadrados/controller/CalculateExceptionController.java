package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.CalculateResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestControllerAdvice(annotations = RestController.class)
public class CalculateExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    public ErrorDTO processField( List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Ups! Validations error: ", fields);
    }

    // My custom exception
    @ExceptionHandler(DistrictNotFoundException.class)
    public ResponseEntity<Object> handlerExceptions(DistrictNotFoundException ex){
        CalculateResponseDTO exceptionResponse = new CalculateResponseDTO();
        exceptionResponse.setResponse(String.format("Ups! Invalid input parameters: %s ", ex.getMessage()));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
