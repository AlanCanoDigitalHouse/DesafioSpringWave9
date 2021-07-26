package com.example.desafiotesting.service;

import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.EnvironmentResponseDTO;
import com.example.desafiotesting.dto.response.ResponseDTO;
import com.example.desafiotesting.exception.DistrictNotFoundException;
import com.example.desafiotesting.repository.DistrictRepository;
import com.example.desafiotesting.util.TestUtilGenerator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.Utilities;

import static org.mockito.Mockito.*;


import java.util.List;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    PropertyService propertyService;

    @Test
    public void calculateTest() throws DistrictNotFoundException {
        PropertyDTO property = TestUtilGenerator.getProperty();

        when(districtRepository.propertyExists(property.getDistrict().getDistrict_name())).thenReturn(true);

        ResponseDTO response = propertyService.calculateAll(property);

        verify(districtRepository, atLeastOnce()).propertyExists(property.getDistrict().getDistrict_name());

        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals(4290000.0, response.getPropertyPrice());
                    Assertions.assertEquals(4290.0,response.getPropertySize());
                    Assertions.assertEquals(990.0, response.getBiggerEnvironment().getEnvironmentSize());
                    Assertions.assertEquals("habitación5", response.getBiggerEnvironment().getEnvironment());
                });
    }

    @Test
    public void calculatePropertySizeTest() {
        List<EnvironmentDTO> list = TestUtilGenerator.getEnvironmentList();

        Double propertySize = propertyService.calculatePropertySize(list);

        Assertions.assertEquals(4290.0, propertySize);
    }

    @Test
    public void calculateBiggerEnvironmentTest() {
        List<EnvironmentDTO> list = TestUtilGenerator.getEnvironmentList();

        EnvironmentResponseDTO environmentBigger = propertyService.calculateBiggerEnvironment(list);
        EnvironmentResponseDTO environmentBiggerExpected = new EnvironmentResponseDTO("habitación5", 990.0);

        Assertions.assertEquals(environmentBiggerExpected.getEnvironment(), environmentBigger.getEnvironment());
    }

    @Test
    public void calculateEachSizeEnvironmentTest() {
        List<EnvironmentDTO> list = TestUtilGenerator.getEnvironmentListWithOneItem();

        List<EnvironmentResponseDTO> propertySizeByEnvironment = propertyService.calculateEachSizeEnvironment(list);

        Assertions.assertAll(
                () -> propertySizeByEnvironment.forEach(environment -> {
                    Assertions.assertEquals(825.0, environment.getEnvironmentSize());
                })
        );
    }

}