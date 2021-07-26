package com.mercado_libre.bootcamp.desafio2.unittest.services;

import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.NeighborhoodRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.RoomRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.mercado_libre.bootcamp.desafio2.services.house.implementation.HouseCalculatorService;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.implementation.NeighborhoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HouseCalculatorServiceTests {

    @Mock
    NeighborhoodService neighborhoodService;

    @InjectMocks
    HouseCalculatorService service;

    RoomRequestDTO smallerRoomRequest;

    RoomRequestDTO largestRoomRequest;

    List<RoomRequestDTO> rooms;

    HouseRequestDTO request;

    HouseResponseDTO response;

    @BeforeEach
    private void setUp() {
        smallerRoomRequest = new RoomRequestDTO("Habitación", 20D, 30D);
        largestRoomRequest = new RoomRequestDTO("Living", 25D, 33D);
        rooms = new ArrayList<>();
        rooms.add(smallerRoomRequest);
        rooms.add(largestRoomRequest);
        request = new HouseRequestDTO("Casa de Karen", new NeighborhoodRequestDTO("Caseros", 40D), rooms);
        response = service.calculate(request);
    }

    @Test
    public void m2CalculatedAreOK() {
        double expectedm2 = smallerRoomRequest.getSquareMeters() + largestRoomRequest.getSquareMeters();
        Assertions.assertEquals(expectedm2, response.getSquaresMeters());
    }

    @Test
    public void ifYouSearchTheLargestRoomItWillReturnIt() {
        Assertions.assertEquals(largestRoomRequest.getSquareMeters(), response.getLargestRoom().getSquaresMeters());
    }

    @Test
    public void m2CalculatedPerRoomAreOK() {
        double expectedm2 = smallerRoomRequest.getSquareMeters() + largestRoomRequest.getSquareMeters();
        Assertions.assertEquals(expectedm2, response.getSquaresMeters());
    }

    @Test
    public void valuePerSquaresMetersIsOK() {
        double expectedm2 = (smallerRoomRequest.getSquareMeters() + largestRoomRequest.getSquareMeters()) * request.getDistrict().getDistrict_price();
        Assertions.assertEquals(expectedm2, response.getValuePerSquaresMeters());
    }

}
