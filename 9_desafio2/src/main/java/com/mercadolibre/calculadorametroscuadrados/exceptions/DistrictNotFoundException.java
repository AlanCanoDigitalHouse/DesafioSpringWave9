package com.mercadolibre.calculadorametroscuadrados.exceptions;

public class DistrictNotFoundException extends AplicationException {
    public DistrictNotFoundException() {
        data = "Barrio no encontrado";
    }
}
