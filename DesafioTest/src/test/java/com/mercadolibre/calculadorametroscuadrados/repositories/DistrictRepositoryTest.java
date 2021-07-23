package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import org.junit.jupiter.api.*;

import java.util.HashMap;

public class DistrictRepositoryTest {


    DistrictRepository districtRepository = new DistrictRepositoryImpl();

    @Test
    @DisplayName("Test Find District By Name")
    public void findDistrictByName() {
        // arrange
        DistrictDTO expected = new DistrictDTO();
        expected.setDistrictName("Palermo");

        // act
        DistrictDTO current = districtRepository.findDistrictByDistrictName("Palermo");

        // assert
        Assertions.assertEquals(current, expected);
    }

    @Test
    @DisplayName("Test Not Found District By Name")
    public void notFoundDistrictByName() {
        // arrange
        ErrorDTO expected = new ErrorDTO(404, "El distrito Parque Patricios no se encuentra registrado.", new HashMap<>());

        // act && assert
        DistrictNotFoundException exception = Assertions.assertThrows(DistrictNotFoundException.class, () -> districtRepository.findDistrictByDistrictName("Parque Patricios"));
        Assertions.assertEquals(exception.getErrorDTO(), expected);
    }
}
