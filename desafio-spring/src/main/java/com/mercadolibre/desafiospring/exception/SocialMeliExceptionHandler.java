package com.mercadolibre.desafiospring.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@ResponseBody
public class SocialMeliExceptionHandler {

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
    public ResponseEntity<String> handleFormatException
            (HttpMessageNotReadableException e) {
        // e.printStackTrace();
        return new ResponseEntity<>(filterJsonParseException(e.getMessage()),
                 HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SocialMeliException.class)
    public ResponseEntity<String> handleDataException(SocialMeliException e) {
        // e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleUnexistingEndpoint(
            NoHandlerFoundException e) {
        return new ResponseEntity<>("Invalid endpoint.", HttpStatus.NOT_FOUND);
    }
}