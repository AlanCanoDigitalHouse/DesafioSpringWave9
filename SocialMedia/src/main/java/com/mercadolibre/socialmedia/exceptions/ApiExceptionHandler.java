package com.mercadolibre.socialmedia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserIdException.class)
    public ResponseEntity<ErrorDto> handleException(UserIdException exception) {
        String error = "Invalid UserId.";
        ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND, exception.getMsg(), error);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FollowException.class)
    public ResponseEntity<ErrorDto> handleException(FollowException exception) {
        String error = "Follow Exception.";
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, exception.getMsg(), error);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, "Follow Exception.", errors);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestParamException.class)
    public ResponseEntity<ErrorDto> handleException(RequestParamException exception) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST,  exception.getMsg(),"RequestParamException");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorDto> handleException(ParseException exception) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, "The format of the field Date is invalid.","ParseException");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FieldErrorDto handleException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private FieldErrorDto processFieldErrors(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new FieldErrorDto(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleException(MethodArgumentTypeMismatchException ex) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleException(HttpMessageNotReadableException ex) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "HttpMessageNotReadableException" );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
