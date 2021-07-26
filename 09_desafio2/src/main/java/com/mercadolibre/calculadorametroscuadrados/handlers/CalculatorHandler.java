package com.mercadolibre.calculadorametroscuadrados.handlers;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.EnvironmentRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.helpers.EnvironmentHelper;

public class CalculatorHandler {


    public static EnvironmentResponseDTO squareFeetEnvironment(EnvironmentRequestDTO environment) {

        EnvironmentResponseDTO environmentSquare = EnvironmentHelper.mapper(environment);
        environmentSquare.setSquareFeet(environmentSquare.getWidth() * environmentSquare.getLength());
        return environmentSquare;
    }

}
