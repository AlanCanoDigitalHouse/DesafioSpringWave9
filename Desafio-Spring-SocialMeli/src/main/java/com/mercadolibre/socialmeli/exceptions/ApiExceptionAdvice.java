package com.mercadolibre.socialmeli.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mercadolibre.socialmeli.exceptions.users.SameUserTwiceException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyFollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserAlreadyUnfollowException;
import com.mercadolibre.socialmeli.exceptions.users.UserNotFoundException;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionAdvice {

    public ErrorMessage processField( List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(ConstraintViolationException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(MethodArgumentTypeMismatchException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                String.format("The userId '%s' must be '%s'",
                        ex.getValue(),
                        Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(InvalidFormatException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                String.format("The value you enter: '%s' must be '%s'",
                        ex.getValue(),
                        Objects.requireNonNull(ex.getTargetType().getSimpleName())));
    }

    /*@ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(JsonParseException ex) {
        ErrorMessage errorMessage;
        try {
            errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), String.format("The value of '%s' can't be '%s'", ex.getProcessor().getCurrentName(), ex.getRequestPayloadAsString()));
        } catch (IOException e){
            errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "The json wasn't be read");
        }
        return errorMessage;
    }*/
/*
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(DateTimeParseException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),  ex.getLocalizedMessage());
    }*/

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(HttpMessageNotReadableException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),  ex.getMostSpecificCause().getLocalizedMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerSameUser(SameUserTwiceException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerAlreadyFollow(UserAlreadyFollowException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerAlreadyFollow(UserAlreadyUnfollowException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerUserNotFound(UserNotFoundException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }



}
