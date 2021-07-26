package com.example.tucasitatasaciones.units.services;

import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.example.tucasitatasaciones.exceptions.DistrictException;
import com.example.tucasitatasaciones.repositories.DistrictRepository;
import com.example.tucasitatasaciones.services.PropertyServiceImpl;
import com.example.tucasitatasaciones.units.utils.UnitTestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTests {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    PropertyServiceImpl propertyService;

    @Test
    void districtNotFoundException() {
        // Arrange
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.getDistrict().setDistrict_name("Not Found");
        // Act
        when(districtRepository.checkIfDistrictExistsFor(property)).thenReturn(Boolean.FALSE);
        // Assert
        Assertions.assertThrows(DistrictException.class, () -> propertyService.calculateProperty(property));
    }

    @Test
    void districtCalculate() throws DistrictException {
        // Arrange
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.setProp_name("");
        PropertyResponseDTO expectedPropertyResponse = UnitTestBuilder.property1Response2Env();
        // Act
        when(districtRepository.checkIfDistrictExistsFor(property)).thenReturn(Boolean.TRUE);
        // Assert
        assertEquals(expectedPropertyResponse, propertyService.calculateProperty(property));
    }

    @Test
    void totalMts2IsOk() throws DistrictException {
        // Arrange
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        PropertyResponseDTO expectedPropertyResponse = UnitTestBuilder.property1Response2Env();
        // Act
        when(districtRepository.checkIfDistrictExistsFor(property)).thenReturn(Boolean.TRUE);
        // Assert
        assertEquals(expectedPropertyResponse.getTotalMts2(), propertyService.calculateProperty(property).getTotalMts2());
    }

    @Test
    void biggestEnviromentIsOk() throws DistrictException {
        // Arrange
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        PropertyResponseDTO expectedPropertyResponse = UnitTestBuilder.property1Response2Env();
        // Act
        when(districtRepository.checkIfDistrictExistsFor(property)).thenReturn(Boolean.TRUE);
        // Assert
        assertEquals(expectedPropertyResponse.getBiggestEnviroment(), propertyService.calculateProperty(property).getBiggestEnviroment());
    }

    @Test
    void enviromentMts2IsOk() throws DistrictException {
        // Arrange
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        PropertyResponseDTO expectedPropertyResponse = UnitTestBuilder.property1Response2Env();
        // Act
        when(districtRepository.checkIfDistrictExistsFor(property)).thenReturn(Boolean.TRUE);
        // Assert
        assertEquals(expectedPropertyResponse.getEnviroments(), propertyService.calculateProperty(property).getEnviroments());
    }
}
