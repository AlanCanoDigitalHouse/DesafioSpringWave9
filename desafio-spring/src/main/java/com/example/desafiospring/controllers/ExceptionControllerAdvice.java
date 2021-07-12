package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.ErrorMessageDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handlerException(MethodArgumentTypeMismatchException exception) {
        String message = String.format("'%s' deberia ser un '%s' valido, y '%s' no lo es",
                exception.getName(), exception.getRequiredType().getSimpleName(),
                exception.getValue());
        return new ResponseEntity<>(
                new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), message, null),
                HttpStatus.BAD_REQUEST);
    }

    private ErrorMessageDto processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<String, String>();
        for(FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validations error", fields);
    }

}
