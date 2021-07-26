package com.mercadolibre.calculadorametroscuadrados.unit.handler;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.EnvironmentRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.Found.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.handlers.CalculatorHandler;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.IDistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorHandlerTest {

    @Test
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
