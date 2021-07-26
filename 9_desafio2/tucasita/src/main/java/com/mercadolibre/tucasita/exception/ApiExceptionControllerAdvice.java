package com.mercadolibre.tucasita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> validationExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult br = e.getBindingResult();
        return loadFieldErrorMap(br.getFieldErrors());
    }

    private Map<String,String> loadFieldErrorMap(List<FieldError> fields) {
        Map<String,String> result = new HashMap<>();

        for(FieldError fe : fields) {
            result.put(fe.getField(), fe.getDefaultMessage());
        }

        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity invalidInputTypeExceptionHandler(HttpMessageNotReadableException e) {
        return new ResponseEntity("Invalid input.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity responseStatusExceptionHandler(ResponseStatusException e) {
        return new ResponseEntity(e.getMessage(), e.getStatus());
    }
}
