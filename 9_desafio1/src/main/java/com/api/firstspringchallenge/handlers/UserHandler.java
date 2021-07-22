package com.api.firstspringchallenge.handlers;

import com.api.firstspringchallenge.exceptions.DuplicatedFollowException;
import com.api.firstspringchallenge.exceptions.UnableToFollowYourselfException;
import com.api.firstspringchallenge.exceptions.UnexistingUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.firstspringchallenge.utils.ErrorMessageUtils.createErrorMessage;

@ControllerAdvice(annotations = RestController.class)
public class UserHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerUnistingUserException(UnexistingUserException exception) {

        return new ResponseEntity(createErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerDuplicatedFollowException(DuplicatedFollowException exception) {

        return new ResponseEntity(createErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerUnableToFollowYourselfException(UnableToFollowYourselfException exception) {

        return new ResponseEntity(createErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}
