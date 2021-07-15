package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.response.ResponseErrorDTO;
import com.meli.socialmeli.exceptions.*;
import com.meli.socialmeli.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SocialMeliExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialMeliExceptionController.class);

    @ExceptionHandler(UserNullException.class)
    public ResponseEntity<ResponseErrorDTO> UserNotFoundExceptionHandler(UserNullException ex){
        LOGGER.info("No se encontro al usuario solicitado.");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ResponseErrorDTO> DataBaseExceptionHandler(DataBaseException ex){
        LOGGER.info("No se pudo cargar la base de datos.");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepeatedPostException.class)
    public ResponseEntity<ResponseErrorDTO> RepeatedPostExceptionHandller(RepeatedPostException ex){
        LOGGER.info("Ya existe una publicacion con ese id.");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNullException.class)
    public ResponseEntity<ResponseErrorDTO> ProductNullExceptionHandller(ProductNullException ex){
        LOGGER.info("Ya existe un producto con ese id.");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> DateNotValidException(DateNotValidException ex){
        LOGGER.info("La fecha ingresada deber ser mayor a la actual.");
        return new ResponseEntity<>(ex.getErrorDTO(), HttpStatus.BAD_REQUEST);
    }
}
