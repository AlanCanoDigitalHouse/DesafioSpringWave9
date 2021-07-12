package com.example.desafio1.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {
    @ExceptionHandler(value = {UserNotFoundException.class, TypeMismatchException.class,
                        NumberFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error badRequestHandler(Exception ex){
        return new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
