package com.mercadolibre.tucasita.exception;

public class DistrictNotFoundException extends ApplicationException{
    private static String notFoundMessage = "No se encuentra el barrio en la base de datos";

    public DistrictNotFoundException() {
        super(notFoundMessage);
    }
}
