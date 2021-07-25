package com.mercadolibre.calculadorametroscuadrados.units.dtos;

import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.models.EnvironmentModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Unit Test - EnvironmentDTO")
public class EnvironmentDTOUnitTest {

    @Test
    @DisplayName("Unit Test - method: getSquareFeet - 1")
    void getSquareFeet_1() {
        // arrange
        Double expected = 132.0;
        EnvironmentDTO environmentDTO = EnvironmentDTO.builder().environment_name("test").environment_width(12.0).environment_length(11.0).build();
        // act
        Double current = environmentDTO.getSquareFeet();
        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Unit Test - method: getSquareFeet - 2")
    void getSquareFeet_2() {
        // arrange
        Double expected = 1.0;
        EnvironmentDTO environmentDTO = EnvironmentDTO.builder().environment_name("test").environment_width(12.0).environment_length(11.0).build();
        // act
        Double current = environmentDTO.getSquareFeet();
        // assert
        assertNotEquals(expected, current);
    }
}
