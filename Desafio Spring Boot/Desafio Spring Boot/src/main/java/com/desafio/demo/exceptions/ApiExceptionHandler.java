package com.desafio.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = InvalidIdUserException.class)
    public ResponseEntity<ErrorMessage> handleException(InvalidIdUserException e){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(InvalidIdUserException.message, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidIdPostException.class)
    public ResponseEntity<Void> handleException(InvalidIdPostException e){
        return ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(value = InvalidOrderingCriterionException.class)
    public ResponseEntity<ErrorMessage> handleException(InvalidOrderingCriterionException e){
        return ResponseEntity.badRequest().body(new ErrorMessage(InvalidOrderingCriterionException.message, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(value = InvalidDateFormatException.class)
    public  ResponseEntity<ErrorMessage>  handleException(InvalidDateFormatException e){
        return ResponseEntity.badRequest().body(new ErrorMessage(InvalidDateFormatException.message, HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(InteractionNotAllowedException e){
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handlerException(RuntimeException runtimeException){
        return  new ResponseEntity<ErrorMessage>(new ErrorMessage(runtimeException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
