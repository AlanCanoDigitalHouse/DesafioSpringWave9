package com.meli.tasaciones.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<MetrosCuadradosErrorMessage> errorMessage(MethodArgumentNotValidException exception) {
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    Map<String, String> fieldErrorsMap = fieldErrors.stream().collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
    MetrosCuadradosErrorMessage errorMessage = new MetrosCuadradosErrorMessage("Datos invalidos", fieldErrorsMap);
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

}
