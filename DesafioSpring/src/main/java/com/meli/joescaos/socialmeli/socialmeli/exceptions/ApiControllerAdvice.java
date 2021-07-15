package com.meli.joescaos.socialmeli.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(UserNotFoundException ex) {
        return new ErrorMessage(ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessage handler(UserAlreadyFollowedException ex) {
        return new ErrorMessage(ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessage handler(PostNotFoundException ex) {
        return new ErrorMessage(ex.ERROR);
    }

}
