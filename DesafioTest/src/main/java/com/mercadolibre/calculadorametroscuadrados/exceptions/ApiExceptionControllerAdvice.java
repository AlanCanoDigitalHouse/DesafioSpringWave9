package com.mercadolibre.calculadorametroscuadrados.exceptions;

import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorDTO errorDTO = processField(fieldErrors);
        return handlerException(new CalculateExceptionController(errorDTO));
    }

    public ErrorDTO processField(List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", fields);
    }

    @ExceptionHandler(CalculateExceptionController.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handlerException(CalculateExceptionController exception) {
        return new ResponseEntity<>(exception.getErrorDTO(), HttpStatus.valueOf(exception.getErrorDTO().getStatus()));

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handlerException(HttpMessageNotReadableException exception) {
        return handlerException(new CalculateExceptionController(HttpStatus.BAD_REQUEST.value(), "Bad Request Format",
                new HashMap<>()));
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorDTO> handlerException(HttpRequestMethodNotSupportedException exception) {
        return handlerException(new CalculateExceptionController(HttpStatus.BAD_REQUEST.value(), "Bad Method " + exception.getMethod(),
                new HashMap<>()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handlerException(NoHandlerFoundException exception) {
        return handlerException(new CalculateExceptionController(HttpStatus.NOT_FOUND.value(), "Not Found " + exception.getRequestURL(),
                new HashMap<>()));
    }


}
