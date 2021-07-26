package com.meli.desafioTest.exception;

import org.springframework.http.HttpStatus;

public class DistrictNotFoundException extends CalculateException {

    public DistrictNotFoundException(String name) {
        super("El barrio " + name + " no se encuentra en nuestros registros.", HttpStatus.NOT_FOUND);
    }
}
