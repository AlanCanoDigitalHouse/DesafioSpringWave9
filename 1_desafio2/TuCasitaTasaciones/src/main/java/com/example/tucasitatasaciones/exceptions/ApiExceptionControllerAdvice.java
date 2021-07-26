package com.example.tucasitatasaciones.exceptions;

import com.example.tucasitatasaciones.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<ErrorDto> handleGlobalExceptions(GetDistrictException e) {
        return new ResponseEntity<>(new ErrorDto("get district",e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}