package com.mercadolibre.calculadorametroscuadrados.unit.controller;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFound;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictPriceNotMatch;

import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import util.UtilGenerator;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Controller Unit Test")
public class CalculateRestControllerTest {

    @Mock
    CalculateService service;

    @InjectMocks
    CalculateRestController controller;

    @Test
    public void calculateTest() throws DistrictNotFound, DistrictPriceNotMatch {
        //arrange
        HouseDTO house = UtilGenerator.genHousePerfectCase();
        //act
        controller.calculate(house);
        //assert
        verify(service, atLeastOnce()).calculate(house);
    }
}
