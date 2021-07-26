package com.mercadolibre.calculadorametroscuadrados.unit.controllers;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.mercadolibre.calculadorametroscuadrados.utils.testUtils;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class CalculateRestControllerTest {
    @Mock
    CalculateService service;
    
    @InjectMocks
    CalculateRestController controllerTest;

    @Test
    void shouldCalculateHouseSquareMetersAndReturnHouseResponseDTO() {
        //arrange
        HouseDTO houseDTO = testUtils.generate1RoomHouse();

        //act
        controllerTest.calculate(houseDTO);

        //assert
        Mockito.verify(service, Mockito.atLeastOnce()).calculate(houseDTO);
    }
}
