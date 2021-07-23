package com.example.tucasitatasaciones.exceptions;

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
    public ErrorMessage districtNotFound(DistrictException ex) {
        HashMap<String, String> fields = new HashMap<>();
        fields.put(ex.getClass().getSimpleName(), ex.ERROR);
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), "District Not Found", fields);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        HashMap<String, String> fields = new HashMap<>();
        fieldErrors.stream().forEach(fieldError -> fields.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

}
