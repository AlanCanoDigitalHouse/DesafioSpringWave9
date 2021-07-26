package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDataDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.CalculateException;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@ControllerAdvice
public class CalculateExceptionController {

    /* TODO: Para excepciones con estatus y error*/
    @ExceptionHandler(CalculateException.class)
    ResponseEntity<ErrorDTO> handleGlobalExceptions(CalculateException e){
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

    /* TODO: Para excepciones peticiones con metodo no valido*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /* TODO: Para excepciones peticiones con data erronea no esperada */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(HttpMessageNotReadableException e) {
        ErrorDTO error = new ErrorDTO("HttpMessageNotReadableException", "La data ingresada no es la requerida: " + e.getCause());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /* TODO: Para excepciones en procesos*/
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataDTO handlerExecptionDataNotFound(DataNotFound ex){
        HashMap<String, String> fields = new HashMap<>();
        fields.put(ex.getError(), ex.getReason());
        return new ErrorDataDTO(HttpStatus.BAD_REQUEST.value(), ex.ERROR,fields);
    }
}
