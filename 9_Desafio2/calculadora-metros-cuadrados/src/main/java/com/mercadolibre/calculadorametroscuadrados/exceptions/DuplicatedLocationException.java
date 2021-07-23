package com.mercadolibre.calculadorametroscuadrados.exceptions;

public class DuplicatedLocationException extends RuntimeException{
    private String location;

    public DuplicatedLocationException(String location) {
        this.location = location;
    }

    public String getMessage(){
        return "Location " + location + " already exists";
    }
}
