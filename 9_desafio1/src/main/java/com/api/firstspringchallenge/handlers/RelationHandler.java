package com.api.firstspringchallenge.handlers;

import com.api.firstspringchallenge.exceptions.UnexistingRelationException;
import com.api.firstspringchallenge.exceptions.UnexistingUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.firstspringchallenge.utils.ErrorMessageUtils.createErrorMessage;

@ControllerAdvice(annotations = RestController.class)
public class RelationHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerUnexistingRelationException(UnexistingRelationException exception) {
        return new ResponseEntity(createErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }


}
