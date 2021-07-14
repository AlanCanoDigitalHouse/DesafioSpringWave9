package com.example.desafio1springboot.exceptions;

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
    public ErrorMessage userSellerNotFound(UserSellerNotFoundExceptions ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userAlreadyFollowingSeller(UserAlreadyFollowingSellerException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage userClientDoesNotExistsInDatabase(UserClientDoesNotExistsException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    public ErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

}
