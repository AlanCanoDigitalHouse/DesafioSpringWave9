package com.mercadolibre.tucasitatasaciones.exception;

public class DistrictNotFoundException extends Exception {

    public DistrictNotFoundException(String value) {
        super("The district '" + value + "' cannot be found!");
    }

}
