package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.ErrorMessageDto;
import com.example.desafiospring.exceptions.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler(AlreadyFollowedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handlerException(AlreadyFollowedException exception) {
        return new ResponseEntity<>(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handlerException(UserNotExistException exception) {
        return new ResponseEntity<>(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SameUserException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handlerException(SameUserException exception) {
        return new ResponseEntity<>(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFollowedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handlerException(UserNotFollowedException exception) {
        return new ResponseEntity<>(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handlerException(MethodArgumentTypeMismatchException exception) {
        String message = String.format("'%s' deberia ser un '%s' valido, y '%s' no lo es",
                exception.getName(), Objects.requireNonNull(exception.getRequiredType()).getSimpleName(),
                exception.getValue());
        return new ResponseEntity<>(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), message, null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handlerException(InvalidFormatException exception) {
        String message = String.format("'%s' deberia ser de tipo '%s' valido",
                exception.getValue(),exception.getTargetType().getSimpleName());
        return new ResponseEntity<>(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), message, null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleException(ConstraintViolationException e){
        return ResponseEntity.badRequest().body(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),e.getMessage(), null));
    }

    @ExceptionHandler(DiscountInvalidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleException(DiscountInvalidException e){
        return ResponseEntity.badRequest().body(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),e.getMessage(), null));
    }

    @ExceptionHandler(DateInvalidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleException(DateInvalidException e){
        return ResponseEntity.badRequest().body(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),e.getMessage(), null));
    }

    @ExceptionHandler(PromoPostInvalidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleException(PromoPostInvalidException e){
        return ResponseEntity.badRequest().body(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),e.getMessage(), null));
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleException(IOException e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),"Hubo un problema leyendo la base de datos", null));
    }

    private ErrorMessageDto processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for(FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validations error", fields);
    }

}
