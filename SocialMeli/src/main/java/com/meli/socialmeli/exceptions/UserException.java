package com.meli.socialmeli.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException extends Exception{

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserException.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void nullUserHandler(){
        LOGGER.info("No se encontro usuario con los datos seleccionados");
    }
}
