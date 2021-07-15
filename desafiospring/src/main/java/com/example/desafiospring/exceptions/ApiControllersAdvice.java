package com.example.desafiospring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllersAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotFoundByIdException ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(IndexOutOfBoundsException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(NumberFormatException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserAlreadyFollowToSellerException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotFollowSellerException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(OrderNotExistsException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(PostNotFoundByIdException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(PostAlreadyExistsException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(ProductAlreadyExistsException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(HttpMessageNotReadableException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handler(MethodArgumentNotValidException notvalid) {
        return new ResponseEntity("there is an invalid attribute when initializing post",HttpStatus.BAD_REQUEST);
    }




}
