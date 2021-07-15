package com.mercadolibre.social_meli.exception;

import com.mercadolibre.social_meli.dto.response.ErrorFieldsResponse;
import com.mercadolibre.social_meli.dto.response.ErrorResponse;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(HttpMessageNotReadableException exception) {
        return new ErrorResponse("An error occurred while parsing your HTTP request", HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(JsonParseException exception) {
        return new ErrorResponse("An error occurred while parsing the JSON", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(DatabaseException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(ResourceNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(NoEffectRequest exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(InvalidValueException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidArgument(MethodArgumentNotValidException exception) {
        var errorResults = exception.getBindingResult();
        var errorFields = processErrorFields(errorResults.getFieldErrors());

        return new ErrorFieldsResponse("Validation Error", HttpStatus.BAD_REQUEST.value(), errorFields);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidArgument(MethodArgumentTypeMismatchException exception) {
        return new ErrorResponse("An argument is of the wrong type", HttpStatus.BAD_REQUEST.value());
    }

    private Map<String, String> processErrorFields(List<FieldError> fieldErrors) {
        var mappedErrors = new HashMap<String, String>();

        for (var fieldError:fieldErrors) {
            mappedErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return mappedErrors;
    }

}
