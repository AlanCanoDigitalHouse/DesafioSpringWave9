package com.mercadolibre.tucasitaTasaciones.unitTests.repositories;

import com.mercadolibre.tucasitaTasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;
import com.mercadolibre.tucasitaTasaciones.repositories.CalculateRepository;
import com.mercadolibre.tucasitaTasaciones.repositories.CalculateRepositoryImpl;
import com.mercadolibre.tucasitaTasaciones.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateRepositoryTests {

    CalculateRepository calculateRepository;

    @BeforeEach
    @AfterEach
    private void setUp() {
        TestUtilsGenerator.districtFile();

        this.calculateRepository = new CalculateRepositoryImpl() {
        };
    }

    @Test
    @DisplayName("Test validar existe barrio")
    public void findDistrictExist() throws ExceptionLocationNotFound, ExceptionLocationPriceIncorrect {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictExist();
        // act
        DistrictDTO foundDistrict = calculateRepository.findPriceLocation(district.getDistrict_name());
        calculateRepository.checkPrice(district);
        // assert
        assertEquals(district, foundDistrict);
    }

    @Test
    @DisplayName("Test barrio no existente")
    public void findDistrictNotExist() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictNotExist();
        // act & assert
        Assertions.assertThrows(ExceptionLocationNotFound.class, () -> calculateRepository.findPriceLocation(district.getDistrict_name()));
    }

    @Test
    @DisplayName("Test precio barrio incorrecto")
    public void findDistrictNotExistPrice() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictPriceNotExist();
        // act & assert
        Assertions.assertThrows(ExceptionLocationPriceIncorrect.class, () -> calculateRepository.checkPrice(district));
    }

}
