package com.example.demo.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice{

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessage handler(UserNotFound ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessage handler(DuplicateUser ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessage handler(UserRemoved ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessage handler(EmptyFile ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(JsonParseException exception){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Some fields are not property fill out");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(InvalidDate exception){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Field format is invalid, the correct one is dd-MM-yyyy");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageValidation handler(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(HttpMessageNotReadableException exception) {
        String result = exception.getMessage();
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Invalid post fields to save");

    }

    public ErrorMessageValidation processField(List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessageValidation(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }


}
