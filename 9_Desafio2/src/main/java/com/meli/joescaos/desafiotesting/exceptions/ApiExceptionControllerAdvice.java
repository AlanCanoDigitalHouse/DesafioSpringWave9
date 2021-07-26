package com.meli.joescaos.desafiotesting.exceptions;

import org.springframework.http.HttpStatus;
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
    public ErrorDto handlerException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFields(fieldErrors);
    }

    public ErrorDtoWithFieldErrors processFields(List<FieldError> fieldErrors){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError: fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDtoWithFieldErrors(HttpStatus.BAD_REQUEST.value(), "Arguments invalids", errors);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handlerException(DistrictNotFoundException e){
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handlerException(PriceErrorException e){
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.ERROR);
    }
}
