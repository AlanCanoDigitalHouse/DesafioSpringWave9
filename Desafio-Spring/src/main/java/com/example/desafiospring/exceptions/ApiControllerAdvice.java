package com.example.desafiospring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.ValidationException;


@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotFindOrEqualException ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotFollowToUserException ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserToFollowAlreadyExistException ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorValidationMessage handlerException(ValidationException exception) {
        return new ErrorValidationMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorValidationMessage handlerException(NumberFormatException exception) {
        return new ErrorValidationMessage(HttpStatus.BAD_REQUEST.value(), "Input wait for an integer", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorValidationMessage handlerException(MethodArgumentNotValidException exception) {
        System.out.println("validation request");
        return new ErrorValidationMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorValidationMessage handlerException(HttpMessageNotReadableException exception) {
        System.out.println("validation request");
        return new ErrorValidationMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", exception.getMessage());
    }



}
