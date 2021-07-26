package com.mercadolibre.calculadorametroscuadrados.unit.repository;

import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.repository.IDistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class DistrictRepositoryTest {

    private IDistrictRepository districtRepository;

    @BeforeEach
    public void setUp() {
        districtRepository = new DistrictRepository();
    }


    @Test
    @DisplayName("Valid Does Not Throw District ")
    void testDistrictNameExist() {
        Assertions.assertDoesNotThrow(() ->
                districtRepository.findNameDistrict("Belgrano", "classpath:static/price.json"));
    }

    @Test
    @DisplayName("Valid Does Not Throw District ")
    void testDistrictNameExistValidPath() {
        Assertions.assertDoesNotThrow(() ->
                districtRepository.findNameDistrict("Belgrano", "classpath:static/price.json"));
    }

    @Test
    @DisplayName("Valid Throw District")
    void testDistrictNameDoesNotExist() {
        Assertions.assertThrows(DistrictNotFoundException.class, () ->
                districtRepository.findNameDistrict("HelloDistrict", ""));
    }

    @Test
    @DisplayName("Valid Throw District")
    void testDistrictNameDoesNotExistInvalidPath() {
        Assertions.assertThrows(DistrictNotFoundException.class, () ->
                districtRepository.findNameDistrict("", "classpath:static/error.json"));
    }

    @Test
    @DisplayName("Valid Path District File ")
    void testPathFound() {
        Assertions.assertDoesNotThrow(() ->
                districtRepository.loadDatabase("classpath:static/price.json"));

    }

    @Test
    @DisplayName("Valid Throw District")
    void testPathNotFound() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                districtRepository.loadDatabase("classpath:static/price"));
    }
}