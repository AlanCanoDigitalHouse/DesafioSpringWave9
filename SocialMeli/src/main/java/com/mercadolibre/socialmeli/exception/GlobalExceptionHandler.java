package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerUserNotFound(UserNotFoundException ex){
        return  new ErrorMessage(ex.getStatus().value(), ex.getReason());
    }

    @ExceptionHandler(UserCannotFollowOneSelfException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handlerUserCannotFollowOneSelf(UserCannotFollowOneSelfException ex){
        return  new ErrorMessage(ex.getStatus().value(), ex.getReason());
    }

    @ExceptionHandler(UserAlreadyFollowSellerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handlerUserAlreadyFollowSeller(UserAlreadyFollowSellerException ex){
        return  new ErrorMessage(ex.getStatus().value(), ex.getReason());
    }
}

