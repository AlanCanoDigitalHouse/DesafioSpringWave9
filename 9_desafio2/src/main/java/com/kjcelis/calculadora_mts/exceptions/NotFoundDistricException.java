package com.kjcelis.calculadora_mts.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundDistricException extends HouseException {
    public NotFoundDistricException() {
        super("Los datos de nombre o precio del distrito no coinciden con datos en el repositorio", HttpStatus.NOT_FOUND);
    }
}

