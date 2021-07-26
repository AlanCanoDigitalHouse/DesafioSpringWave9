package com.mercadolibre.tucasitatasaciones.unit.repository;

import com.mercadolibre.tucasitatasaciones.exception.ResourceNotFoundException;
import com.mercadolibre.tucasitatasaciones.repository.DistrictRepository;
import com.mercadolibre.tucasitatasaciones.util.JSONDataUtil;
import com.mercadolibre.tucasitatasaciones.util.TestDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DistrictRepositoryTest {

    private DistrictRepository districtRepository;
    private static final String DATA_DIR = "classpath:static/districts.json";

    @BeforeEach
    void initRepo() {
        districtRepository = new DistrictRepository();

        JSONDataUtil.cleanData(DATA_DIR);
    }

    @Test
    @DisplayName(value = "Test getDistrictByName returns the right district")
    void testGetDistrictByName() {
        TestDataUtil.initDummyData(DATA_DIR);
        var expectedDistrict = TestDataUtil.getDistrict("DistrictC");

        var result = districtRepository.getDistrictByName(expectedDistrict.getName());

        Assertions.assertEquals(expectedDistrict, result);
    }

    @Test
    @DisplayName(value = "Test getAllDistricts returns all districts")
    void testGetAllDistricts() {
        TestDataUtil.initDummyData(DATA_DIR);
        var expectedCollectionSize = 4;

        var result = districtRepository.getAllDistricts();

        Assertions.assertEquals(expectedCollectionSize, result.size());
    }

    @Test
    @DisplayName(value = "Test there is no exception thrown when the db is empty")
    void testEmptyDBDoesNotThrowError() {
        Assertions.assertDoesNotThrow(() -> districtRepository.getAllDistricts());
    }

    @Test
    @DisplayName(value = "Test a custom exception is thrown when searching for non existing district")
    void testDistrictNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> districtRepository.getDistrictByName("Unknown District"));
    }

    @Test
    @DisplayName(value = "Test a custom exception is thrown when passed null as parameter")
    void testNullAsParameterStillThrowsCustomException() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> districtRepository.getDistrictByName(null));
    }
}
