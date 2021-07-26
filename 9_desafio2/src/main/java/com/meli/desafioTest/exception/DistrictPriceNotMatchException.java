package com.meli.desafioTest.exception;

import org.springframework.http.HttpStatus;

public class DistrictPriceNotMatchException extends CalculateException {

    public DistrictPriceNotMatchException() {
        super("El precio ingresado no coincide con el precio en nuestros registros.", HttpStatus.BAD_REQUEST);
    }
}