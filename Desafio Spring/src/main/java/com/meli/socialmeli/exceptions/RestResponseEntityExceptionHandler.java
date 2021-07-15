package com.meli.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;


@RestControllerAdvice(annotations = RestController.class)
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(FollowException.class)
    public ResponseEntity<ErrorMessage> handlerFollows(FollowException e){
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.ERROR), HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorMessage> handlerUsers(UserDoesNotExistException e){
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.ERROR), HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handlerValidations(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity<>(processField(fieldErrors), HttpStatus.BAD_REQUEST) ;
    }

    public ErrorMessage processField(List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }
}
