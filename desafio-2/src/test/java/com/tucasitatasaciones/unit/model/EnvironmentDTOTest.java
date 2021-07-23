package com.tucasitatasaciones.unit.model;

import com.tucasitatasaciones.DTOs.EnvironmentDTO;
import com.tucasitatasaciones.utils.TestGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@SpringBootTest
public class EnvironmentDTOTest {

    // target: Verificar que efectivamente el total de metros cuadrados por ambiente sea el correcto.
    // output: Devuelve el cálculo correcto del total de metros cuadrados de un ambiente.
    @Test
    public void getSquareEnvironmentTest() {
        // arrange
        List<EnvironmentDTO> environmentDTOS = TestGenerator.getRequestPropertyDTO().getEnvironments();
        double[] currentSquares = new double[environmentDTOS.size()];
        double[] expectedSquares = TestGenerator.getSquaresEnvironments();
        // act
        for (int i = 0; i < environmentDTOS.size(); i++)
            currentSquares[i] = environmentDTOS.get(i).getSquareFeet();
        // assert
        assertArrayEquals(expectedSquares, currentSquares);
    }
}
