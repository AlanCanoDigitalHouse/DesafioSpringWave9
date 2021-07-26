package com.mercado_libre.bootcamp.desafio2.handlers;

import com.mercado_libre.bootcamp.desafio2.exceptions.UnexistingNeighborhoodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mercado_libre.bootcamp.desafio2.utils.ErrorMessageUtils.createErrorMessage;

@ControllerAdvice(annotations = RestController.class)
public class UnexistingNeighborhoodHandler {
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerUnexistingNeighborhoodException(UnexistingNeighborhoodException exception) {
        return new ResponseEntity(createErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

}
