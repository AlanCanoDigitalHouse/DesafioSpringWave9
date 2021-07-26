package com.mercadolibre.tucasitaTasaciones.unitTests.controllers;

import com.mercadolibre.tucasitaTasaciones.controller.CalculateRestController;
import com.mercadolibre.tucasitaTasaciones.dto.PropertyDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;
import com.mercadolibre.tucasitaTasaciones.service.CalculateService;
import com.mercadolibre.tucasitaTasaciones.util.TestUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CalculateRestControllerTests {

    @Mock
    CalculateService calculateService;

    @InjectMocks
    CalculateRestController calculateController;

    @Test
    public void calculate() throws ExceptionLocationPriceIncorrect, ExceptionLocationNotFound {
        // arrange
        PropertyDTO property1 = TestUtilsGenerator.getPropertyWith3Environments("Casa correcta");

        // act
        calculateController.calculate(property1);

        // assert
        verify(calculateService, atLeastOnce()).calculate(property1);
    }



}
