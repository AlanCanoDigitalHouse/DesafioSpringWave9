package com.mercadolibre.tucasita.unit.repository;

import com.mercadolibre.tucasita.domain.District;
import com.mercadolibre.tucasita.repository.DistrictRepositoryImpl;
import com.mercadolibre.tucasita.util.TestDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DistrictRepositoryTest {

    private DistrictRepositoryImpl districtRepository;

    public DistrictRepositoryTest() {
        this.districtRepository = new DistrictRepositoryImpl();
    }

    @Test
    @DisplayName("Test Buscar Distrito Valido por Nombre")
    public void findValidDistrictByNameTest() {

        District expectedDistrict = TestDataGenerator.getDistrictOfValidHouse();
        String districtNameToFind = expectedDistrict.getName();

        District districtFound  = districtRepository.findByName(districtNameToFind);

        assertEquals(expectedDistrict, districtFound);

    }

    @Test
    @DisplayName("Buscar Distrito Invalido por Nombre")
    public void findInvalidDistrictByNameTest() {

        String invalidDistrictName = "puerto";

        District districtFound = districtRepository.findByName(invalidDistrictName);

        assertNull(districtFound);
    }
}
