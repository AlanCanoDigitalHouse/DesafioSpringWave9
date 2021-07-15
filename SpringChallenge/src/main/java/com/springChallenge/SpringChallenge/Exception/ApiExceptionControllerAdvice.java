package com.springChallenge.SpringChallenge.Exception;

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
    public ErrorMessage handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        return processField(errors);
    }

    private ErrorMessage processField(List<FieldError> errors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError error: errors) {
            fields.put(error.getField(), error.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validar datos", fields);
    }

}