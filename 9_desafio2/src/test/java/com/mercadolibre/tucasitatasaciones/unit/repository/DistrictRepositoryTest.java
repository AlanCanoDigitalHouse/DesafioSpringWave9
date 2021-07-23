package com.mercadolibre.tucasitatasaciones.unit.repository;

import com.mercadolibre.tucasitatasaciones.exception.ResourceNotFoundException;
import com.mercadolibre.tucasitatasaciones.repository.DistrictRepository;
import com.mercadolibre.tucasitatasaciones.util.JSONDataUtil;
import com.mercadolibre.tucasitatasaciones.util.TestDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DistrictRepositoryTest {

    private DistrictRepository districtRepository;
    private static final String DATA_DIR = "classpath:static/districts.json";

    @BeforeEach
    void initRepo() {
        districtRepository = new DistrictRepository();

        JSONDataUtil.cleanData(DATA_DIR);
    }

    private void addDummyDistricts() {
        var districtList = new ArrayList<>(){{
            add(TestDataUtil.getDistrict("DistrictA"));
            add(TestDataUtil.getDistrict("DistrictB"));
            add(TestDataUtil.getDistrict("DistrictC"));
            add(TestDataUtil.getDistrict("DistrictD"));
        }};

        JSONDataUtil.addTestData(DATA_DIR, districtList);
    }

    @Test
    void testGetDistrictByName() {
        this.addDummyDistricts();
        var expectedDistrict = TestDataUtil.getDistrict("DistrictC");

        var result = districtRepository.getDistrictByName(expectedDistrict.getName());

        Assertions.assertEquals(expectedDistrict, result);
    }

    @Test
    void testGetAllDistricts() {
        this.addDummyDistricts();
        var expectedCollectionSize = 4;

        var result = districtRepository.getAllDistricts();

        Assertions.assertEquals(expectedCollectionSize, result.size());
    }

    @Test
    void testEmptyDBDoesNotThrowError() {
        Assertions.assertDoesNotThrow(() -> districtRepository.getAllDistricts());
    }

    @Test
    void testDistrictNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> districtRepository.getDistrictByName("Unknown District"));
    }

    @Test
    void testNullAsParameterStillThrowsCustomException() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> districtRepository.getDistrictByName(null));
    }
}
