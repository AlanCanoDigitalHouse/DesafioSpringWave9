package com.example.desafio_spring.controllers;

import com.example.desafio_spring.dtos.response.ErrorMessage;
import com.example.desafio_spring.exceptions.*;
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
    public ErrorMessage handlerException(FollowSameUserException exception) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    @ExceptionHandler(UserAlreadyFollowedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(UserAlreadyFollowedException exception) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    @ExceptionHandler(PostNotExistException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(PostNotExistException exception) {
        return new ErrorMessage(HttpStatus.NO_CONTENT.value(), exception.getMessage());
    }
    @ExceptionHandler(InvalidInputVariableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(InvalidInputVariableException exception) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    /*
    @ExceptionHandler(PasswordNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handlerException(PasswordNotValidException exception){
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }*/
}
