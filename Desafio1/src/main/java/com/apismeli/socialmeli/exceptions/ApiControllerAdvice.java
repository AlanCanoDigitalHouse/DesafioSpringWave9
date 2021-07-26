package com.apismeli.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handler(Exception e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerArgument(MethodArgumentNotValidException notvalid) {
        return new ResponseEntity("there is an invalid attribute when initializing post", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage userNotFound(NotFoundExceptions nF) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), nF.ERROR);
    }


}
