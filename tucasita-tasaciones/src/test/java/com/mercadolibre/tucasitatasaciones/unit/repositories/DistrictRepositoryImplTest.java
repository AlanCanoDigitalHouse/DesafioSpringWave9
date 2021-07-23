package com.mercadolibre.tucasitatasaciones.unit.repositories;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.DistrictRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DistrictRepositoryImplTest {

    private DistrictRepositoryImpl districtRepository;

    @BeforeEach
    void setUp() {
        districtRepository = new DistrictRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testDistrictExists() throws DistrictNotFoundException {
        String expected = "EXISTS";

        DistrictDTO current = districtRepository.findDistrictBy(expected);

        Assertions.assertEquals(expected, current.getDistrictName());
    }

    @Test
    void testDistrictNotExists() {
        String districtName = "NOT EXISTS";

        Assertions.assertThrows(DistrictNotFoundException.class, () -> districtRepository.findDistrictBy(districtName));
    }

}