package com.mercadolibre.tucasitatasaciones.exceptions;

public class DistrictNotFoundException extends Exception{
    private String location;
    public DistrictNotFoundException(String location) {
        this.location = location;
    }

    @Override
    public String getMessage() {
        return "No district " + location + " was found";
    }
}
