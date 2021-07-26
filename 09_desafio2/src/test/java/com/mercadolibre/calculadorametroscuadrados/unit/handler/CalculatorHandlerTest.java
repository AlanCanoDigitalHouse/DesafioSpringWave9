package com.mercadolibre.calculadorametroscuadrados.unit.handler;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.EnvironmentRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.handlers.CalculatorHandler;
import com.mercadolibre.calculadorametroscuadrados.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorHandlerTest {

    @Test
    @DisplayName("Calculate Square Meters Environments")
    public void squareFeetEnvironment() {
        // arrange
        EnvironmentRequestDTO environment = TestUtilsGenerator.getEnvironmentWithName("Room1");

        // act
        EnvironmentResponseDTO result = CalculatorHandler.squareFeetEnvironment(environment);

        // assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(126D, result.getSquareFeet());
    }

    @Test
    @DisplayName("Calculate Square Meters With None Environments")
    public void squareFeetEnvironmentZeroLength() {
        // arrange
        EnvironmentRequestDTO environment = TestUtilsGenerator.getEnvironmentWithName("Room1");
        environment.setLength(0D);
        // act
        EnvironmentResponseDTO result = CalculatorHandler.squareFeetEnvironment(environment);

        // assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0D, result.getSquareFeet());
    }

}
