package com.tucasitatasaciones.unit.controllers;

import com.tucasitatasaciones.DTOs.PropertyDTO;
import com.tucasitatasaciones.controllers.CalculateRestController;
import com.tucasitatasaciones.services.CalculateService;
import com.tucasitatasaciones.utils.TestGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CalculateRestControllerTest {

    @Mock
    CalculateService service;

    @InjectMocks
    CalculateRestController controller;

    @Test
    public void obtenerDiploma() {
        // arrange
        PropertyDTO prop = TestGenerator.getRequestPropertyDTO();

        // act
        controller.calculate(prop);

        // assert
        verify(service, atLeastOnce()).calculate(prop);
    }
}
