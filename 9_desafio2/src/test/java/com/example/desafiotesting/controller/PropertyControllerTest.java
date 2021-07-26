package com.example.desafiotesting.controller;

import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.exception.DistrictNotFoundException;
import com.example.desafiotesting.service.PropertyService;
import com.example.desafiotesting.util.TestUtilGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {
    @Mock
    PropertyService service;

    @InjectMocks
    PropertyController controller;

    @Test
    public void calculateTest() throws DistrictNotFoundException {
        // arrange
        PropertyDTO property = TestUtilGenerator.getProperty();

        // act
        controller.calculate(property);

        // assert
        verify(service, atLeastOnce()).calculateAll(property);
    }
}
