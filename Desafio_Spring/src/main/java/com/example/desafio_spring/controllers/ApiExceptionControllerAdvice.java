package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.response.ErrorMessage;
import com.example.desafio_spring.exceptions.FollowSameUserException;
import com.example.desafio_spring.exceptions.UserAlreadyFollowedException;
import com.example.desafio_spring.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {
    @ExceptionHandler(UserNotExistException.class) //Manipula el metoodo para dar las respuestas erroneas
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorMessage handlerException(UserNotExistException exception) {
        return new ErrorMessage(HttpStatus.NO_CONTENT.value(), exception.getMessage());
    }
    @ExceptionHandler(FollowSameUserException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handlerException(FollowSameUserException exception) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyFollowedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handlerException(UserAlreadyFollowedException exception) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
    /*
    @ExceptionHandler(PasswordNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handlerException(PasswordNotValidException exception){
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }*/
}
