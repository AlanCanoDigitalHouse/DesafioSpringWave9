package com.kjcelis.calculadora_mts.unit.repository;

import com.kjcelis.calculadora_mts.dto.DistrictDTO;
import com.kjcelis.calculadora_mts.exceptions.NotFoundDistricException;
import com.kjcelis.calculadora_mts.repository.DistrictRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit Test - DistrictRepositoryImpl")
public class DistrictRepositoryImplTest {
    DistrictRepositoryImpl repository = new DistrictRepositoryImpl();


    @DisplayName("Existencia del distrito")
    @Test
    void existDistrict() throws NotFoundDistricException {
        String barrio = "Bosa";
        DistrictDTO expected = new DistrictDTO("BOSA", 500);
        DistrictDTO current = repository.findDistrictRepo(barrio, 500);
        Assertions.assertEquals(expected, current);
    }

    @DisplayName("No existe el distrito y salta la excepción")
    @Test
    void notFoundDistrict() {
        Assertions.assertThrows(NotFoundDistricException.class, () -> repository.findDistrictRepo("NOT", 4));
    }
}
