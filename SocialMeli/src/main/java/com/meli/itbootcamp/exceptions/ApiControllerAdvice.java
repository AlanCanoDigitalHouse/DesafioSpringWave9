package com.meli.itbootcamp.exceptions;

import com.meli.itbootcamp.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO badRequest(UserException ex) {
        return ExceptionDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .messge(ex.getMessage())
                .details(null)
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO badRequest(PostException ex) {
        return ExceptionDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .messge(ex.getMessage())
                .details(null)
                .build();
    }


}
