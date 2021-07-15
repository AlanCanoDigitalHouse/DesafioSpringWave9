package com.mercadolibre.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageException handlerException(ExceptionUserNotFound exception) {
        return new ErrorMessageException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessageException> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        ErrorMessageException errorMessage = new ErrorMessageException(HttpStatus.BAD_REQUEST.value(),
                "Fallo al intentar convertir valor");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessageException> httpMessageNotReadableExceptionHandler(MethodArgumentTypeMismatchException exception) {
        ErrorMessageException errorMessage = new ErrorMessageException(HttpStatus.BAD_REQUEST.value(),
                "Fallo al intentar convertir valor");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageException handlerException(ExcepcionEqualsUserId exception) {
        return new ErrorMessageException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageException handlerException(ExceptionFollower exception) {
        return new ErrorMessageException(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageException handlerException(ExceptionOrder exception) {
        return new ErrorMessageException(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    private ErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validation error", fields);
    }

}
