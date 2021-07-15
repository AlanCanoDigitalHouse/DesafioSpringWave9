package com.example.socialmeli.exceptions;

import com.example.socialmeli.dtos.responses.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsg handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFlied(fieldErrors);

    }

    private ErrorMsg processFlied(List<FieldError> fieldErrors) {
        HashMap<String,String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return new ErrorMsg(HttpStatus.BAD_REQUEST.value(), "validation errors", fields);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handlerNotFoundException(NotFoundException exception){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handlerResponseStatusExeption(ResponseStatusException exception){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handlerResponseStatusExeption(HttpMessageNotReadableException exception){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto("Invalid format"),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handlerResponseStatusExeption(MethodArgumentTypeMismatchException exception){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto("Invalid params"),HttpStatus.BAD_REQUEST);
    }

}