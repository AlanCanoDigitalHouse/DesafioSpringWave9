package com.example.desafiotesting.service;

import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    @InjectMocks
    PropertyService propertyService;

    @Test
    public void calculatePropertySizeTest() {
        List<EnvironmentDTO> list = TestUtilGenerator.getEnvironmentList();

        Double propertySize = propertyService.calculatePropertySize(list);

        Assertions.assertEquals(500.0, propertySize);
    }

}