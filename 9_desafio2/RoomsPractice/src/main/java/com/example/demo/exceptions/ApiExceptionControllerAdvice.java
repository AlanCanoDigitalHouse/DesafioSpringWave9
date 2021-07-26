package com.example.demo.exceptions;

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
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessageException handler(DistrictNotFound ex){
        return new ErrorMessageException(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageValidations handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    public ErrorMessageValidations processField(List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessageValidations(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

}
