package com.mercadolibre.tucasitatasaciones.unit.controller;

import com.mercadolibre.tucasitatasaciones.controller.PropertyController;
import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.LargestEnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyEnvironmentsAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyTotalAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyValuationDTO;
import com.mercadolibre.tucasitatasaciones.service.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @Test
    void testTotalAreaServiceCallAndResponse() {
        var requestBody = new PropertyRequestDTO();
        var expectedBody = new PropertyTotalAreaDTO();
        var expectedStatus = HttpStatus.OK.value();

        Mockito.when(propertyService.calculatePropertyTotalArea(requestBody))
                .thenReturn(expectedBody);

        var response = this.propertyController.calculatePropArea(requestBody);

        Mockito.verify(propertyService, Mockito.atLeast(1))
                .calculatePropertyTotalArea(requestBody);
        Assertions.assertAll(
                () -> Assertions.assertEquals(response.getBody(), expectedBody),
                () -> Assertions.assertEquals(response.getStatusCodeValue(), expectedStatus)
        );
    }

    @Test
    void testValuationServiceCallAndResponse() {
        var requestBody = new PropertyRequestDTO();
        var expectedBody = new PropertyValuationDTO();
        var expectedStatus = HttpStatus.OK.value();

        Mockito.when(propertyService.valuateProperty(requestBody))
                .thenReturn(expectedBody);

        var response = this.propertyController.valuateProp(requestBody);

        Mockito.verify(propertyService, Mockito.atLeast(1))
                .valuateProperty(requestBody);
        Assertions.assertAll(
                () -> Assertions.assertEquals(response.getBody(), expectedBody),
                () -> Assertions.assertEquals(response.getStatusCodeValue(), expectedStatus)
        );
    }

    @Test
    void testLargestEnvironmentServiceCallAndResponse() {
        var requestBody = new PropertyRequestDTO();
        var expectedBody = new LargestEnvironmentDTO();
        var expectedStatus = HttpStatus.OK.value();

        Mockito.when(propertyService.determineLargestEnvironment(requestBody))
                .thenReturn(expectedBody);

        var response = this.propertyController.determineLargestEnvironment(requestBody);

        Mockito.verify(propertyService, Mockito.atLeast(1))
                .determineLargestEnvironment(requestBody);
        Assertions.assertAll(
                () -> Assertions.assertEquals(response.getBody(), expectedBody),
                () -> Assertions.assertEquals(response.getStatusCodeValue(), expectedStatus)
        );
    }

    @Test
    void testEnvironmentsAreaServiceCallAndResponse() {
        var requestBody = new PropertyRequestDTO();
        var expectedBody = new PropertyEnvironmentsAreaDTO();
        var expectedStatus = HttpStatus.OK.value();

        Mockito.when(propertyService.calculateEnvironmentsArea(requestBody))
                .thenReturn(expectedBody);

        var response = this.propertyController.calculateEnvironmentsArea(requestBody);

        Mockito.verify(propertyService, Mockito.atLeast(1))
                .calculateEnvironmentsArea(requestBody);
        Assertions.assertAll(
                () -> Assertions.assertEquals(response.getBody(), expectedBody),
                () -> Assertions.assertEquals(response.getStatusCodeValue(), expectedStatus)
        );
    }
}
