package com.example.demo.exceptions.userExceptions.advices;

import com.example.demo.exceptions.userExceptions.Exceptions.UserNotFoundException;
import com.example.demo.exceptions.userExceptions.models.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<BadRequest> handlerUserNotFoundException(
            UserNotFoundException e
    ) {
        return new ResponseEntity<>(new BadRequest(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }
}
