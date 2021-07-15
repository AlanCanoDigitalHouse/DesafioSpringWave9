package com.mercadolibre.socialmedia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;


@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserIdException.class)
    public ResponseEntity<ErrorDto> handleException(UserIdException exception) {
        ErrorDto errorDto = new ErrorDto("Invalid UserId.", exception.getMsg());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FollowException.class)
    public ResponseEntity<ErrorDto> handleException(FollowException exception) {
        ErrorDto errorDto = new ErrorDto("Follow Exception.", exception.getMsg());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleException(ConstraintViolationException exception) {
        ErrorDto errorDto = new ErrorDto("ConstraintViolationException", "Error in PathVariabls");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestParamException.class)
    public ResponseEntity<ErrorDto> handleException(RequestParamException exception) {
        ErrorDto errorDto = new ErrorDto("RequestParamException", exception.getMsg());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorDto> handleException(ParseException exception) {
        ErrorDto errorDto = new ErrorDto("ParseException", "The format of the field Date is invalid.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FieldErrorDto methodArgumentNotValidException(MethodArgumentNotValidException ex) {
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
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(HttpMessageNotReadableException ex) {
        ErrorDto errorDto = new ErrorDto("HttpMessageNotReadableException", ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
