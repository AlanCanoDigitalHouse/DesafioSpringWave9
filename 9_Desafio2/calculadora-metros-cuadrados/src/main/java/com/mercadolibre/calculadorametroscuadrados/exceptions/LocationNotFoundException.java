package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.Getter;

@Getter
public class LocationNotFoundException extends RuntimeException{
    private String location;

    public LocationNotFoundException(String location) {
        this.location = location;
    }

    public String getMessage(){
        return "Location " + location + " not found";
    }
}
