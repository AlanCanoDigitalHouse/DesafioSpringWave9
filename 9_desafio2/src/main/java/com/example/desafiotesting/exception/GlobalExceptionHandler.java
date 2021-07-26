package com.example.desafiotesting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(DistrictNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageError handlerUserNotFound(DistrictNotFoundException ex){
        return  new MessageError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
