package com.example.tucasitatasaciones.units.controllers;

import com.example.tucasitatasaciones.controllers.PropertyRestController;
import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.example.tucasitatasaciones.exceptions.DistrictException;
import com.example.tucasitatasaciones.services.PropertyService;
import com.example.tucasitatasaciones.units.utils.UnitTestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyRestControllerTests {

    @Mock
    PropertyService propertyService;

    @InjectMocks
    PropertyRestController controller;

    @Test
    void calculateProperty() throws DistrictException {
        // Arrange
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        PropertyResponseDTO expectedResponse = UnitTestBuilder.property1Response2Env();
        // Act
        when(propertyService.calculateProperty(property)).thenReturn(expectedResponse);
        // Assert
        Assertions.assertEquals(new ResponseEntity<>(expectedResponse, HttpStatus.OK), controller.calculateProperty(property));
    }

    @Test
    void calculatePropertyDistrictException() throws DistrictException {
        // Arrange
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.getDistrict().setDistrict_name("Not Found");
        // Act
        when(propertyService.calculateProperty(property)).thenThrow(new DistrictException("Not Found"));
        // Assert
        Assertions.assertThrows(DistrictException.class, () -> controller.calculateProperty(property));
    }
}
