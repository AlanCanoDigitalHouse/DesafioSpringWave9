package com.mercadolibre.desafio1.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mercadolibre.desafio1.dto.exceptions.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handlerException(UserNotExistException exception){
        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handlerException(UserFollowException exception){
        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handlerException(DateAfterNowException exception){
        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handlerException(MethodArgumentTypeMismatchException exception){
        String message = String.format("El parametro '%s' debe ser de tipo '%s'.",exception.getValue(),exception.getRequiredType().getSimpleName());
        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handlerException(InvalidFormatException exception){
        String message = String.format("El parametro '%s' debe ser de tipo '%s'.",exception.getValue(),exception.getTargetType().getSimpleName());
        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), message);
    }

    //Validations
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handlerException(ConstraintViolationException exception){
        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDTO handlerException(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processField(fieldErrors);
    }

    public ErrorMessageDTO processField(List<FieldError> fieldErrors){
        HashMap<String, String> fields = new HashMap<>();

        for(FieldError fieldError:fieldErrors){
            fields.put(fieldError.getField(),fieldError.getDefaultMessage());
        }

        return new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), "Validations error.", fields);
    }
}
