package com.tucasitatasaciones.controllers;

import com.tucasitatasaciones.DTOs.ErrorDTO;
import com.tucasitatasaciones.exceptions.BadRequestException;
import com.tucasitatasaciones.globalconstants.Message;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class HandlerExceptionController {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ErrorDTO handleValidationExceptions(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequestClientException(Exception exception) {
        HashMap<String, String> message = new HashMap<>();
        message.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorDTO(HttpStatus.BAD_REQUEST, Message.WRONG_INPUT_PROVIDED, message);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGeneralInternalException(Exception exception) {
        HashMap<String, String> message = new HashMap<>();
        message.put(exception.getClass().getSimpleName(), exception.getMessage());
        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_PROCESS_ERROR, message);
    }

    private ErrorDTO processField(List<FieldError> fieldErrors) {
        Map<String, String> fields = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDTO(HttpStatus.BAD_REQUEST, Message.WRONG_INPUT_PROVIDED, fields);
    }
}