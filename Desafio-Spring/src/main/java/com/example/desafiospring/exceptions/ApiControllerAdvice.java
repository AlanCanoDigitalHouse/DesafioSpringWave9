package com.example.desafiospring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotFindException ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotFollowToUser ex){
        return  new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.ERROR);
    }

}
