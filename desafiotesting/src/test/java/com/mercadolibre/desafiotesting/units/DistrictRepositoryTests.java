package com.mercadolibre.desafiotesting.units;

import com.mercadolibre.desafiotesting.exceptions.DistrictException;
import com.mercadolibre.desafiotesting.repositories.DistrictRepository;
import com.mercadolibre.desafiotesting.repositories.DistrictRepositoryImpl;
import org.junit.jupiter.api.*;

class DistrictRepositoryTests {

    DistrictRepository districtRepository;

    @BeforeEach
    @AfterEach
    private void setUp() {
        this.districtRepository = new DistrictRepositoryImpl();
    }

    @Test
    @DisplayName("Find district test")
    void findExistentDistrict() {

        String found = null;
        try {
            found = districtRepository.findDistrictByName("Barrio flores");

        } catch (DistrictException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("Barrio flores", found);
    }

    @Test
    @DisplayName("Find non existent district test")
    void findNonExistentDistrict() {

        Assertions.assertThrows(DistrictException.class, () -> districtRepository.findDistrictByName("Barrio null"));
    }
}
