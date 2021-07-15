package com.example.desafio1.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {
    @ExceptionHandler(value = {UserNotFoundException.class
            ,TypeMismatchException.class,
            NumberFormatException.class,
            UserAlreadyFollowingException.class,
            MismatchedInputException.class,
            JsonParseException.class,
            DateTimeParseException.class,
            MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error badRequestHandler(Exception ex) {
        return new Error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
