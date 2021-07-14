package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.response.ErrorMessage;
import com.example.desafio_spring.exceptions.UserAlreadyFollowedException;
import com.example.desafio_spring.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {
    @ExceptionHandler(UserNotExistException.class) //Manipula el metoodo para dar las respuestas erroneas
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorMessage handlerException(UserNotExistException exception) {
        return new ErrorMessage(HttpStatus.NO_CONTENT.value(), exception.getMessage());
    }
    @ExceptionHandler(UserAlreadyFollowedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handerException(UserAlreadyFollowedException exception) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),  exception.getMessage());
    }
    /*
    @ExceptionHandler(PasswordNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handlerException(PasswordNotValidException exception){
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }*/
}
