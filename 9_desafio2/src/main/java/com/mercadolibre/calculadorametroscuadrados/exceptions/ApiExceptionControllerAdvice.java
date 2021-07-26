package com.mercadolibre.calculadorametroscuadrados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)

public class ApiExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handlerDistrictNotFoundException(DistrictNotFoundException exception) {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Detail", exception.getData());
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), "Error en nombre barrio", fields);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerIncorrectDistrictPriceException(IncorrectDistrictPriceException exception) {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Detail", exception.getData());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Error en precio barrio", fields);
    }

    public ErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Errores de Validacion", fields);
    }

}