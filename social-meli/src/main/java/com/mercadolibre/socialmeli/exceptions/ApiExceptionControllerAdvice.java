package com.mercadolibre.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
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
    public ErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    public ErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Please check request", fields);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequest(UserBadRequest exception) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.ERROR, null);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public ErrorMessage expectationFailed(ExpectationFailed exception) {
        return new ErrorMessage(HttpStatus.EXPECTATION_FAILED.value(), exception.ERROR, null);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    public ErrorMessage preconditionError(PreconditionError exception) {
        return new ErrorMessage(HttpStatus.PRECONDITION_FAILED.value(), exception.ERROR, null);
    }
}