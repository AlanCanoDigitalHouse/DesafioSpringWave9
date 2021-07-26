package com.mercadolibre.calculadorametroscuadrados.exception.Found;

import com.mercadolibre.calculadorametroscuadrados.exception.CalculateHouseException;
import org.springframework.http.HttpStatus;

public class DistrictNotFoundException extends CalculateHouseException {

    public DistrictNotFoundException(String name) {
        super("The District with a Name " + name + " is not registered.", HttpStatus.NOT_FOUND);
    }
}
