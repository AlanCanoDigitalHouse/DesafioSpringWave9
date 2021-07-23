package com.mercadolibre.calculadorametroscuadrados.exceptions;

import java.util.HashMap;

public class DistrictNotFoundException extends CalculateExceptionController {


    public DistrictNotFoundException(Integer status, String name) {
        super(status, "El distrito " + name + " no se encuentra registrado.", new HashMap<>());
    }
}
