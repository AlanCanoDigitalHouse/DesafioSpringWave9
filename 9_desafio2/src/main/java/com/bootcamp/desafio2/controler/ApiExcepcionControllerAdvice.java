package com.bootcamp.desafio2.controler;

import com.bootcamp.desafio2.dtos.response.ErrorMessageDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExcepcionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleException(BusinessException e){
        return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),e.getMessage(), null);
    }


    @ExceptionHandler(IOException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleException(IOException e){
        return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(),e.getMessage(), null);
    }

    private ErrorMessageDto processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<String, String>();
        for(FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validations error", fields);
    }

}
