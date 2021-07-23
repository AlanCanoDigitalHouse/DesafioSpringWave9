package com.mercadolibre.tucasitatasaciones.unit.service;

import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.*;
import com.mercadolibre.tucasitatasaciones.exception.InvalidRequestData;
import com.mercadolibre.tucasitatasaciones.repository.DistrictRepository;
import com.mercadolibre.tucasitatasaciones.service.PropertyService;
import com.mercadolibre.tucasitatasaciones.util.TestDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private DistrictRepository districtRepository;

    @InjectMocks
    private PropertyService propertyService;

    @Test
    void testCalculateTotalArea() {
        var testProp = TestDataUtil.getPropertyFromDistrict("testDistrict");
        var expectedReturn = new PropertyTotalAreaDTO(testProp.getName(), 300D);

        var result = this.propertyService.calculatePropertyTotalArea(testProp);

        Assertions.assertEquals(result, expectedReturn);
    }

    @Test
    void testValuateProperty() {
        var testProp = TestDataUtil.getPropertyFromDistrict("testDistrict");
        var expectedReturn = new PropertyValuationDTO(testProp.getName(), 30000D);

        Mockito.when(districtRepository.getDistrictByName("testDistrict")).thenReturn(testProp.getDistrict());

        var result = this.propertyService.valuateProperty(testProp);

        Mockito.verify(districtRepository, Mockito.atLeastOnce()).getDistrictByName("testDistrict");
        Assertions.assertEquals(result, expectedReturn);
    }

    @Test
    @DisplayName("Test throw an exception if the price provided on the request does not match the db")
    void testValuatePropertyWrongPrice() {
        var testProp = TestDataUtil.getPropertyFromDistrict("testDistrict");
        var actualDistrict = new DistrictDTO("testDistrict", 50D);

        Mockito.when(districtRepository.getDistrictByName("testDistrict")).thenReturn(actualDistrict);

        Assertions.assertThrows(InvalidRequestData.class, () -> propertyService.valuateProperty(testProp));
        Mockito.verify(districtRepository, Mockito.atLeastOnce()).getDistrictByName("testDistrict");
    }

    @Test
    void testDetermineLargestEnvironment() {
        var testProp = TestDataUtil.getPropertyWithDifferentEnvironments("testDistrict");
        var expectedReturn = new LargestEnvironmentDTO(testProp.getName(),
                new EnvironmentAreaDTO("testEnv3", 200D));

        var result = this.propertyService.determineLargestEnvironment(testProp);

        Assertions.assertEquals(result, expectedReturn);
    }

    @Test
    void testCalculateEnvironmentsArea() {
        var testProp = TestDataUtil.getPropertyFromDistrict("testDistrict");
        var expectedData = testProp.getEnvironments()
                .stream().map(e -> new EnvironmentAreaDTO(e.getName(), 100D))
                .collect(Collectors.toList());

        var expectedReturn = new PropertyEnvironmentsAreaDTO(testProp.getName(), expectedData);

        var result = this.propertyService.calculateEnvironmentsArea(testProp);

        Assertions.assertEquals(result, expectedReturn);
    }
}
