package com.example.desafiotesting.unit.controller;

import com.example.desafiotesting.controllers.CalculateRestController;
import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.services.CalculateService;
import com.example.desafiotesting.unit.TestUtilsGenerator;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Calculate Success Test")
    public void calculateSuccessTest() throws Exception {
        // arrange
        PropertyDTO payload = TestUtilsGenerator.getValidProperty();
        
        // act
        controller.calculate(payload);

        // assert
        verify(service, atLeastOnce()).calculate(payload);
    }

}
