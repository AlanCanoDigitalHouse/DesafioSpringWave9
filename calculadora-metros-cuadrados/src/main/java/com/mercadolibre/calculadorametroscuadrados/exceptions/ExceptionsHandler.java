package com.mercadolibre.calculadorametroscuadrados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage argumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return this.processField(fieldErrors);
    }

    private ErrorMessage processField( List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Error en payload", fields);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(Exception exception){
        HashMap<String, String> fields = new HashMap<>();
        fields.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Hubo un error", fields);
    }

}
