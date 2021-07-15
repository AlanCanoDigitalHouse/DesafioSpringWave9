package com.mercado_libre.bootcamp.spring.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    public ErrorMessage handlerException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), methodArgumentTypeMismatchException.getMessage(), "Alguno de los tipos de datos ingresados no es correcto");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(HttpMessageNotReadableException httpMessageNotReadableException) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), httpMessageNotReadableException.getMessage(), "El formato de alguno de los campos no es correcto");
    }

    @ExceptionHandler({SellerRepositoryException.class, UserRepositoryException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(RuntimeException repositoryException) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), repositoryException.getLocalizedMessage(), "Por favor, ingresá un userId válido");
    }

    private ErrorMessages processField(List<FieldError> fieldErrors) {
        Map<String, String> fields = new HashMap<>();

        for (FieldError field : fieldErrors) {
            fields.put(field.getField(), field.getDefaultMessage());
        }

        return new ErrorMessages(HttpStatus.BAD_REQUEST.value(), "Validations error", fields);
    }
}
