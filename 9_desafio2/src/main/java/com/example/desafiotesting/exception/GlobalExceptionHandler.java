package com.example.desafiotesting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(PropertyNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageError handlerUserNotFound(PropertyNotFoundException ex){
        return  new MessageError(ex.getStatus().value(), ex.getReason());
    }
}
