package com.meli.tasaciones.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class MetrosCuadradosRequestException {

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public MetrosCuadradosErrorMessage errorMessage(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    return proccessField(fieldErrors);
  }

  public MetrosCuadradosErrorMessage proccessField(List<FieldError> fieldErrors) {
    Map<String, String> fields = fieldErrors.stream().collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
    return new MetrosCuadradosErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations Failed", fields);
  }
}
