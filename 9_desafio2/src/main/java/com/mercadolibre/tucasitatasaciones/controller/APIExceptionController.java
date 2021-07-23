package com.mercadolibre.tucasitatasaciones.controller;

import com.mercadolibre.tucasitatasaciones.dto.response.ErrorDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.ErrorWithFieldsDTO;
import com.mercadolibre.tucasitatasaciones.exception.DatabaseException;
import com.mercadolibre.tucasitatasaciones.exception.InvalidRequestData;
import com.mercadolibre.tucasitatasaciones.exception.ResourceNotFoundException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class APIExceptionController {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleRequestError(ResourceNotFoundException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleRequestError(InvalidRequestData exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleRequestError(HttpMessageNotReadableException exception) {
        var message = "An error occurred while parsing your HTTP request";

        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleInternalError(DatabaseException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleInternalError(JsonParseException exception) {
        var message = "An error occurred while parsing the JSON";

        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorWithFieldsDTO handleInvalidArgument(MethodArgumentNotValidException exception) {
        var errorResults = exception.getBindingResult();
        var errorFields = processErrorFields(errorResults.getFieldErrors());

        return new ErrorWithFieldsDTO(HttpStatus.BAD_REQUEST.value(), "Validation Error", errorFields);
    }

    private Map<String, String> processErrorFields(List<FieldError> fieldErrors) {
        var mappedErrors = new HashMap<String, String>();

        for (var fieldError:fieldErrors) {
            mappedErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return mappedErrors;
    }

}
