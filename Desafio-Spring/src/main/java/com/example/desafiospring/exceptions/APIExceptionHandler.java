package com.example.desafiospring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGeneralException(Exception exception) {
        exception.printStackTrace();
        String message = exception.getMessage();
        return new ErrorMessage(message);
    }
}
