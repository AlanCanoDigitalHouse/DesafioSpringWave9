package com.mercadolibre.calculadorametroscuadrados.unit.repositories;

import com.mercadolibre.calculadorametroscuadrados.dtos.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DistrictRepositoryImplTest {


    DistrictRepositoryImpl repository = new DistrictRepositoryImpl();

    @Test
    @DisplayName("Existing District")
    void getExistingDistrict() throws DistrictNotFoundException {
        //Arrange
        DistrictDTO districtRequest = new DistrictDTO("Belgrano", 1100.0);
        //Act
        Double result = repository.findPriceDistrict(districtRequest);
        //Assert
        assertEquals(districtRequest.getDistrict_price(), result);
    }

    @Test
    @DisplayName("Inexisting District")
    void getInExistingDistrict() {
        //Arrange
        DistrictDTO district = new DistrictDTO("XXXXX", 900.0);
        //Act & Assert
        assertThrows(DistrictNotFoundException.class, () -> repository.findPriceDistrict(district));
    }


}