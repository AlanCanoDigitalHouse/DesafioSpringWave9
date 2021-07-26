package com.mercadolibre.calculadorametroscuadrados.exceptions;

public class IncorrectDistrictPriceException extends AplicationException {
    public IncorrectDistrictPriceException() {
        data = "Precio no coincide con la BD";
    }
}
