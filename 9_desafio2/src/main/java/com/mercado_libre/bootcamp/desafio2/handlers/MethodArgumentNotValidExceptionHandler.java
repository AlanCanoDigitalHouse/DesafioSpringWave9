package com.mercado_libre.bootcamp.desafio2.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mercado_libre.bootcamp.desafio2.utils.ErrorMessageUtils.createErrorMessage;

@ControllerAdvice(annotations = RestController.class)
public class MethodArgumentNotValidExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity(createErrorMessage(fieldErrors.get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }

}
