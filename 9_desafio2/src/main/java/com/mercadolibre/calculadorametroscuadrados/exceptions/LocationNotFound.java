package com.mercadolibre.calculadorametroscuadrados.exceptions;

public class LocationNotFound extends AppException{
    public LocationNotFound(){
        message="No se tiene información de la locación ingresada.";
    }
}
