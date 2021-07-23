package com.mercadolibre.tucasitatasaciones.unit.repositories;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.DistrictRepositoryImpl;
import org.junit.jupiter.api.*;

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
    @DisplayName("district found (repo)")
    void testDistrictExists() throws DistrictNotFoundException {
        String expected = "EXISTS";

        DistrictDTO current = districtRepository.findDistrictBy(expected);

        Assertions.assertEquals(expected, current.getDistrictName());
    }

    @Test
    @DisplayName("district not found (repo)")
    void testDistrictNotExists() {
        String districtName = "NOT EXISTS";

        Assertions.assertThrows(DistrictNotFoundException.class, () -> districtRepository.findDistrictBy(districtName));
    }

}