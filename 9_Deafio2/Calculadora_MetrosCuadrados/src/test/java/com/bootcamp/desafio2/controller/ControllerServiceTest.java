package com.bootcamp.desafio2.controller;

import com.bootcamp.desafio2.dtos.request.DistrictDTO;
import com.bootcamp.desafio2.dtos.request.EnvironmentDTO;
import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerServiceTest {

    @InjectMocks
    PropertyController propertyController;

    @Mock
    PropertyService propertyService;


    @Test
    @DisplayName("Validación controler - Calculo area total")
    void calculateAreaTest() throws DistrictNotExistsException, PriceNotMatchException {
        //arrange
        HouseDTO house = TestUtilsGenerator.getDefaultHouse();
        HouseResponseDTO houseMock = TestUtilsGenerator.getDefaultHouseResponse();

        //act
        when(propertyService.calculateArea(house)).thenReturn(houseMock);
        ResponseEntity<HouseResponseDTO> current =  propertyController.getArea(house);

        //assert
        verify(propertyService, atLeastOnce()).calculateArea(Mockito.any());
        assertEquals(61, current.getBody().getTotalArea());

    }


    @Test
    @DisplayName("Validación controler - Obtener ambiente mas grande")
    void getBiggestRoomTest() throws DistrictNotExistsException, PriceNotMatchException {
        //arrange
        HouseDTO house = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO expected = new EnvironmentDTO("Comedor", 7D, 4D, 28D);

        HouseResponseDTO houseMock = TestUtilsGenerator.getDefaultHouseResponse();

        //act
        when(propertyService.calculateArea(house)).thenReturn(houseMock);
        ResponseEntity<HouseResponseDTO> current =  propertyController.getArea(house);

        //assert
        verify(propertyService, atLeastOnce()).calculateArea(Mockito.any());
        assertEquals(expected, current.getBody().getBiggestRoom());

    }

    @Test
    @DisplayName("Validación controler - Calcular precio")
    void calculatePriceTest() throws DistrictNotExistsException, PriceNotMatchException {
        //arrange
        HouseDTO house = TestUtilsGenerator.getDefaultHouse();
        HouseResponseDTO houseMock = TestUtilsGenerator.getDefaultHouseResponse();

        //act
        when(propertyService.calculateArea(house)).thenReturn(houseMock);
        ResponseEntity<HouseResponseDTO> current =  propertyController.getArea(house);

        //assert
        verify(propertyService, atLeastOnce()).calculateArea(Mockito.any());
        assertEquals(67100D, current.getBody().getHousePrice());

    }
}


