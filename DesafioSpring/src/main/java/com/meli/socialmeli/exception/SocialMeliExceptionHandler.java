package com.meli.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class SocialMeliExceptionHandler {

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ExceptionMessage handleException(MethodArgumentNotValidException exception) {
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    Map<String, String> fields = fieldErrors.stream().collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
    return new ExceptionMessage(exception.getMessage(), fields);
  }

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ExceptionMessage handleException(ConstraintViolationException exception) {
    Map<String, String> fields =
            exception.getConstraintViolations().stream().collect(Collectors.toMap(constraintViolation -> constraintViolation.getMessage(), constraintViolation -> constraintViolation.getInvalidValue().toString()));
    return new ExceptionMessage(exception.getMessage(), fields);
  }

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ExceptionMessage handleException(MethodArgumentTypeMismatchException exception) {
    HashMap<String, String> stringStringHashMap = new HashMap<>();
    stringStringHashMap.put(exception.getName(), exception.getMessage());
    return new ExceptionMessage(exception.getMessage(), stringStringHashMap);
  }
}
