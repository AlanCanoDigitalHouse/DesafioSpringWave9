package com.bootcamp.desafio2.controller;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @Mock
    PropertyService propertyService;

    @InjectMocks
    PropertyController propertyController;


    @Test
    void calculateArea() throws DistrictNotFoundException, IOException, ErrorMessage {

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House1");

        when(propertyService.calculatePrice(propertyDto)).thenReturn(new ResponseDto(136.0,136000.0,"Bedroom", propertyDto.getEnvironments()));

        ResponseDto responseDto = propertyController.calculateArea(propertyDto).getBody();

        verify(propertyService, atLeastOnce()).calculatePrice(propertyDto);
        Assertions.assertEquals(responseDto.getPropertyPrice(), 136000.0);

    }



}
