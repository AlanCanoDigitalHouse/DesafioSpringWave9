package com.meli.bootcamp.exceptions;

public class DistrictException extends Exception{

    public static final String DISTRICT_NOTFOUND = "El barrio ingresado no se corresponde a uno existente";
    public DistrictException(String message) {
        super(message);
    }
}
