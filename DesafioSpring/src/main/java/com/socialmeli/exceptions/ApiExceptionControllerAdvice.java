package com.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ObjectError> objectErrors = result.getGlobalErrors();
        return processField(fieldErrors, objectErrors);
    }

    public ErrorMessage processField(List<FieldError> fieldErrors, List<ObjectError> objectErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        for (ObjectError objectError : objectErrors) {
            fields.put(Objects.requireNonNull(objectError.getArguments())[1].toString(), objectError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handlerException(ApiException exception) {
        return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.valueOf(exception.getErrorMessage().getStatus()));

    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString().split("\\.")[1], violation.getMessage());
        }
        ErrorMessage apiError =
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);
        return apiError;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleConstraintViolation(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();

        ErrorMessage apiError =
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Bad Request", errors);
        return apiError;
    }


}
