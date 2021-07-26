package com.mercado_libre.bootcamp.desafio2.unittest.controllers;

import com.mercado_libre.bootcamp.desafio2.controllers.HouseCalculatorController;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.NeighborhoodRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.RoomRequestDTO;
import com.mercado_libre.bootcamp.desafio2.services.house.implementation.HouseCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HouseCalculatorContollerTests {

    @Mock
    HouseCalculatorService houseCalculatorService;

    @InjectMocks
    HouseCalculatorController controller;

    @Test
    public void getsCalculatesFromRequest() {
        RoomRequestDTO room = new RoomRequestDTO("Living", 25, 33);
        List<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(room);
        HouseRequestDTO request = new HouseRequestDTO("Casa de Karen", new NeighborhoodRequestDTO("Caseros", 40D), rooms);
        controller.calculate(request);
        verify(houseCalculatorService, atLeastOnce()).calculate(request);
    }

}
