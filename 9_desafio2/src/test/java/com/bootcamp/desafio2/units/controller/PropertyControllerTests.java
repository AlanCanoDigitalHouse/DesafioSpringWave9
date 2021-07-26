package com.bootcamp.desafio2.units.controller;

import com.bootcamp.desafio2.controller.PropertyController;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTests {
    @Mock
    PropertyService propertyService;

    @InjectMocks
    PropertyController propertyController;

    @Test()
    @DisplayName("Calculate total area with default property controller test")
    public void calculateTotalAreaDefaultPropertyControllerTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        ResponseDto responseDto = TestUtilsGenerator.getDefaultPropertyResponse();
        Mockito.when(propertyService.calculatePrice(propertyDto)).thenReturn(responseDto);

        // act
        ResponseEntity<ResponseDto> result = propertyController.calculateArea(propertyDto);

        // assert
        verify(propertyService, atLeastOnce()).calculatePrice(Mockito.any());
        assertEquals(result.getBody().getTotalArea(), 25.0);
    }

    @Test()
    @DisplayName("Calculate total area with bad property controller test")
    public void calculateTotalAreaBadPropertyControllerTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getBadPropertyRequest();
        Mockito.when(propertyService.calculatePrice(propertyDto)).thenThrow(DistrictNotFoundException.class);

        // assert
        Assertions.assertThrows(DistrictNotFoundException.class,() ->  propertyController.calculateArea(propertyDto));
    }

    @Test()
    @DisplayName("Calculate total area with null property controller test")
    public void calculateTotalAreaNullPropertyControllerTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = null;
        Mockito.when(propertyService.calculatePrice(propertyDto)).thenThrow(DistrictNotFoundException.class);

        // assert
        Assertions.assertThrows(DistrictNotFoundException.class,() ->  propertyController.calculateArea(propertyDto));
    }

    @Test()
    @DisplayName("IOException with null property controller test")
    public void iOExceptionAreaNullPropertyControllerTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = null;
        Mockito.when(propertyService.calculatePrice(propertyDto)).thenThrow(IOException.class);

        // assert
        Assertions.assertThrows(IOException.class,() ->  propertyController.calculateArea(propertyDto));
    }
}
