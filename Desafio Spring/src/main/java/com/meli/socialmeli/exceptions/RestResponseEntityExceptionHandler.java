package com.meli.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FollowException.class)
    public ResponseEntity<ErrorMessage> handler(FollowException e){
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.ERROR), HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorMessage> handler(UserDoesNotExistException e){
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.ERROR), HttpStatus.BAD_REQUEST) ;
    }
}
