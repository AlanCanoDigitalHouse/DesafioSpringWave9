package com.mercadolibre.calculadorametroscuadrados.unit.repository;

import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFound;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Repository Unit Test")
public class DistrictRepositoryImplTest {

    DistrictRepositoryImpl repository = new DistrictRepositoryImpl();

    @Test
    public void foundDistrict() throws DistrictNotFound {
        //arrange
        String nameDistrict = "Santiago";
        Double expected = 3000.0;

        //act
        Double result = repository.findDistrictPrice(nameDistrict);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void notFoundDistrict(){
        String nameDistrict = "Rancagua";

        Assertions.assertThrows(DistrictNotFound.class, () -> repository.findDistrictPrice(nameDistrict));
    }
}
