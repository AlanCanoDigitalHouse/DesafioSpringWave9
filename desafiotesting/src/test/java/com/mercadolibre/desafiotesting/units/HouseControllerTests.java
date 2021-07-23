package com.mercadolibre.desafiotesting.units;

import com.mercadolibre.desafiotesting.controllers.HouseController;
import com.mercadolibre.desafiotesting.dto.HouseRequestDto;
import com.mercadolibre.desafiotesting.exceptions.DistrictException;
import com.mercadolibre.desafiotesting.services.HouseService;
import com.mercadolibre.desafiotesting.utils.HouseTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HouseControllerTests {

    @Mock
    HouseService houseService;

    @InjectMocks
    HouseController controller;

    @Test
    @DisplayName("Calculate House test")
    void testCalculateHouse() {

        HouseRequestDto request = HouseTestUtils.getRequestHouse(3000.0);

        try {
            controller.calculate(request);
        } catch (DistrictException e) {
            e.printStackTrace();
        }

        try {
            verify(houseService, atLeastOnce()).calculate(request);
        } catch (DistrictException e) {
            e.printStackTrace();
        }

    }

}
