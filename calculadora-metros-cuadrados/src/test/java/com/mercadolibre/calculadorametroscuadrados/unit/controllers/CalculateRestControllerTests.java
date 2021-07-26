package com.mercadolibre.calculadorametroscuadrados.unit.controllers;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Valid;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CalculateRestControllerTests {

    @Mock
    CalculateService service;

    @InjectMocks
    CalculateRestController controller;

    @Test
    public void passingCorrectlyFormattedPayload() {
        // arrange
        HouseRequestDTO house = HouseRequestInitializer.house();

        // act
        controller.allInOneCalculator(house);

        // assert
        verify(service, atLeastOnce()).allInOneCalculator(house);
    }
}


