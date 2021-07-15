package com.mercadolibre.desafio.spring.handler;


import com.mercadolibre.desafio.spring.exceptions.EmptyListException;
import com.mercadolibre.desafio.spring.exceptions.ErrorUserException;
import com.mercadolibre.desafio.spring.exceptions.InvalidPostException;
import com.mercadolibre.desafio.spring.exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class AppSocialMeliHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<?> invalidUserIDHandlerException(InvalidUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorUserException.class)
    public ResponseEntity<?> errorUserException(ErrorUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<?> emptyList(EmptyListException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPostException.class)
    public ResponseEntity<?> invalidPostHandlerException(InvalidPostException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
