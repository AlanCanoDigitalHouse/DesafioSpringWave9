package com.socialmeli.exceptions;

import com.socialmeli.exceptions.Found.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExcepcionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ListErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handlerException(NotFoundException exception) {
        return new ErrorMessage(exception.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(HttpMessageNotReadableException exception) {
        String[] errorDetails = exception.getLocalizedMessage().split(":");
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("asd",errorDetails[0],errorDetails[1]));
        return processField(fieldErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentTypeMismatchException exception) {
        String errorDetail = exception.getLocalizedMessage().split(";")[0];
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError(exception.getErrorCode(),exception.getName(),errorDetail));
        return processField(fieldErrors);
    }

    public ListErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ListErrorMessage("Validations Error", fields);
    }

}
