package com.mercadolibre.desafio_testing.controller;

import com.mercadolibre.desafio_testing.dto.response.*;
import com.mercadolibre.desafio_testing.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class ExceptionController {
    private static String filterJsonParseException(String msg) {
        int entityNameStart = msg.indexOf("[\"") + 2;
        int entityNameEnd = msg.indexOf("\"]");

        if (entityNameStart > 0 && entityNameEnd > 0) {
            return msg.substring(entityNameStart, entityNameEnd)
                    + " has an unexpected format.";
        }

        return "The body format is invalid.";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleFormatException
            (HttpMessageNotReadableException e) {
        // e.printStackTrace();
        return new ResponseEntity<>(new GenericErrorResponseDTO(
                this.filterJsonParseException(e.getMessage())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericCustomException.class)
    public ResponseEntity<GenericErrorResponseDTO>
    handleDataException(GenericCustomException e) {
        // e.printStackTrace();
        return new ResponseEntity<>(new GenericErrorResponseDTO(
                e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}