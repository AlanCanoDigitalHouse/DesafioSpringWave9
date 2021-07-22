package com.mercadolibre.desafiotesting.unit;

import com.mercadolibre.desafiotesting.exceptions.DistrictException;
import com.mercadolibre.desafiotesting.repositories.DistrictRepository;
import com.mercadolibre.desafiotesting.repositories.DistrictRepositoryImpl;
import org.junit.jupiter.api.*;

public class RepositoryTests {

    DistrictRepository districtRepository;

    @BeforeEach
    @AfterEach
    private void setUp() {

        this.districtRepository = new DistrictRepositoryImpl();
    }

    @Test
    @DisplayName("Test find district")
    public void findExistentDistrict() {

        String found = null;
        try {
            found= districtRepository.findDistrictByName("Barrio flores");

        } catch (DistrictException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals( "Barrio flores",found);
    }

    @Test
    @DisplayName("Test not find district")
    public void findNonExistentDistrict() {


        Assertions.assertThrows(DistrictException.class,() -> districtRepository.findDistrictByName("Barrio null"));

    }
}
