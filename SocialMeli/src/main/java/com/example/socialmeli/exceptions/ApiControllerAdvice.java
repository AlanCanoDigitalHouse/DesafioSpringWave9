package com.example.socialmeli.exceptions;

import com.example.socialmeli.dtos.response.ExceptionResponseDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO exceptionHandler(MethodArgumentNotValidException ex) {
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        Map<String, String> detail = new HashMap<>();
        errorList.forEach(e -> detail.put(e.getField(), e.getDefaultMessage()));
        return ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg("Bad Request:")
                .details(detail)
                .build();
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO notFoundUrl(DataNotFound ex) {
        return ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(ex.getMessage())
                .details(null)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO badRequest(SameUserException ex) {
        return ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(ex.getMessage())
                .details(null)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO parserException(DateParserException ex) {
        return ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(ex.ERROR)
                .details(null)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO badFilter(OrderInvalidFormatException ex) {
        return ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(ex.getMessage())
                .details(null)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO badRequest(MethodArgumentTypeMismatchException ex) {
        Map<String, String> detail = new HashMap<>();
        detail.put(ex.getName() + " : " + ex.getValue(), "Tipo Requerido: " + Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        return ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg("Error de input")
                .details(detail)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO invalidFormat(InvalidFormatException ex) {
        Map<String, String> detail = new HashMap<>();
        detail.put("Value:" + ex.getValue().toString(), ex.getMessage());
        return ExceptionResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg("Error al obtener el Json: ")
                .details(detail)
                .build();
    }
}
