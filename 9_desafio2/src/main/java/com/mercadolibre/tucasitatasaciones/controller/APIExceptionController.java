package com.mercadolibre.tucasitatasaciones.controller;

import com.mercadolibre.tucasitatasaciones.dto.response.ErrorWithFieldsDTO;
import org.springframework.http.HttpStatus;
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
