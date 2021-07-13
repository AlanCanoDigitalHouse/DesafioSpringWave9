package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        StringBuilder errorMessage = new StringBuilder("");
        errors.forEach((__, message) -> errorMessage.append(message));
        return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), errorMessage.toString());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(DateTimeParseException.class)
    public ErrorMessage handleValidationDateExceptions(
            DateTimeParseException ex) {
        return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }
}

