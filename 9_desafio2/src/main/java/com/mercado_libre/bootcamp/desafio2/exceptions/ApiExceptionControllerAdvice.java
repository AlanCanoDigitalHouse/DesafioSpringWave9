package com.mercado_libre.bootcamp.desafio2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessages handlerException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult binding = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = binding.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(HttpMessageNotReadableException httpMessageNotReadableException) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), httpMessageNotReadableException.getMessage(), "El formato del Body ingresado no es correcto");
    }

    @ExceptionHandler({NeighborhoodNotFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(NeighborhoodNotFoundException neighborhoodNotFoundException) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), neighborhoodNotFoundException.getLocalizedMessage(), "Por favor, ingresá un barrio válido");
    }

    @ExceptionHandler({NeighborhoodServiceImplException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(NeighborhoodServiceImplException neighborhoodServiceImplException) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), neighborhoodServiceImplException.getLocalizedMessage(), "Por favor, ingresá un precio válido");
    }

    private ErrorMessages processField(List<FieldError> fieldErrors) {
        Map<String, String> fields = new HashMap<>();

        for (FieldError field : fieldErrors) {
            fields.put(field.getField(), field.getDefaultMessage());
        }

        return new ErrorMessages(HttpStatus.BAD_REQUEST.value(), "Validations error", fields);
    }
}
