package com.mercado_libre.bootcamp.desafio2.units.controllers;

import com.mercado_libre.bootcamp.desafio2.controllers.HouseCalculatorController;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.mercado_libre.bootcamp.desafio2.services.calculator.HouseCalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HouseCalculatorControllerTest {

    @InjectMocks
    private HouseCalculatorController houseCalculatorController;

    @Mock
    private HouseCalculatorService houseCalculatorService;

    @Test
    @DisplayName("GET - /calculate/house - 200")
    void calculate_ok() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();

        ResponseEntity<HouseResponseDTO> response = houseCalculatorController.calculate(houseRequest);

        verify(houseCalculatorService, atMostOnce()).calculate(houseRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
