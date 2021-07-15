package com.jbianchini.meli.socialmeli.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for API exception handling purposes.
 */
@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(HttpMessageNotReadableException exception) {
        Map<String, String> details = new HashMap<>();
        details.put("Detail", exception.getMessage());

        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                "Error found parsing some of the request arguments. Please check the fields", details);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentTypeMismatchException exception) {
        Map<String, String> details = new HashMap<>();
        details.put("Detail", exception.getMessage());

        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                "There was an error parsing some of the request's arguments.", details);

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
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }


}
