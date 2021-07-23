package com.example.tucasita.unit;

import com.example.tucasita.DTO.request.HouseRequestDTO;
import com.example.tucasita.TestingUtils;
import com.example.tucasita.controllers.SquareMeterCalculatorRESTController;
import com.example.tucasita.services.interfaces.HouseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SquareMeterCalculatorRESTControllerTest {

    @Mock
    HouseService houseService;

    @InjectMocks
    SquareMeterCalculatorRESTController controller;

    @Test
    @DisplayName("ValidHouse")
    void testCalculateForValidHouse() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();

        //act
        controller.calculateForHouse(hrDTO);

        //assert
        verify(houseService, atLeastOnce()).calculateForHouse(hrDTO);
    }
}