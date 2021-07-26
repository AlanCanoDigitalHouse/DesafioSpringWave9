package com.mercadolibre.calculadorametroscuadrados.unit;

import com.mercadolibre.calculadorametroscuadrados.TestUtils;
import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Controller - unit Tests")
@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    CalculateService service;

    @InjectMocks
    CalculateRestController controller;

    @Test
    @DisplayName("should call service and status.OK")
    public void calculate() {
        HouseDTO houseDTO = TestUtils.createValidInput();
        controller.calculate(houseDTO);
        Mockito.verify(service, Mockito.atLeastOnce()).calculate(houseDTO);
        assertEquals(HttpStatus.OK, controller.calculate(houseDTO).getStatusCode());
    }

}
