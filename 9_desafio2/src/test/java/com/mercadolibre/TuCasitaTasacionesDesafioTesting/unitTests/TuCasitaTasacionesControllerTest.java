package com.mercadolibre.TuCasitaTasacionesDesafioTesting.unitTests;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.controllers.TuCasitaTasacionesController;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.HouseRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.RoomRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.HouseResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.RoomResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.service.TuCasitaTasacionesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TuCasitaTasacionesControllerTest {

    @Mock
    TuCasitaTasacionesService service;

    @InjectMocks
    TuCasitaTasacionesController controller;

    @Test
    @DisplayName("Method save data - controller test - correct case")
    public void shouldSaveDataHouse() {
        HouseRequestDto request = createHouseRequest();
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.CREATED), controller.saveData(request));
    }

    @Test
    @DisplayName("Method calculate area - controller test - correct case ")
    public void shouldResponseHouseArea() {
        HouseResponseDto response = createHouseResponse();
        when(service.calculateHouseArea()).thenReturn(response);
        Assertions.assertEquals(new ResponseEntity<>(response, HttpStatus.OK), controller.calculateArea());
    }

    @Test
    @DisplayName("Method price by location - controller test - correct case ")
    public void shouldResponsePriceByLocation() {
        HouseResponseDto response = new HouseResponseDto();
        when(service.calculatePriceByLocation()).thenReturn(response);
        Assertions.assertEquals(new ResponseEntity<>(response, HttpStatus.OK), controller.calculatePriceByLocation());
    }

    @Test
    @DisplayName("Method calculate biggest room - controller test - correct case ")
    public void shouldResponseBiggestRoom() {
        RoomResponseDto response = new RoomResponseDto("Cocina", 21.0);
        when(service.calculateBiggestRoom()).thenReturn(response);
        Assertions.assertEquals(new ResponseEntity<>(response, HttpStatus.OK), controller.calculateBiggestRoom());
    }

    @Test
    @DisplayName("Method calculate room area - controller test - correct case ")
    public void shouldResponseRoomArea() {
        HouseResponseDto response = createHouseResponse();
        when(service.calculateRoomArea()).thenReturn(response);
        Assertions.assertEquals(new ResponseEntity<>(response, HttpStatus.OK), controller.calculateRoomArea());
    }

    /**
     * Create a house response
     */
    private HouseResponseDto createHouseResponse() {
        ArrayList<RoomResponseDto> roomsResponse = new ArrayList<>();
        RoomResponseDto room1 = new RoomResponseDto("Cocina", 21.0);
        RoomResponseDto room2 = new RoomResponseDto("Living", 9.0);
        roomsResponse.add(room1);
        roomsResponse.add(room2);
        return new HouseResponseDto(
                " Mi nueva casa",
                30.0,
                105000.0,
                "Recoleta",
                roomsResponse);
    }

    /**
     * Create a house request
     */
    private HouseRequestDto createHouseRequest() {
        ArrayList<RoomRequestDto> roomsRequest = new ArrayList<>();
        RoomRequestDto room1 = new RoomRequestDto("Cocina", 7.0, 3.0);
        RoomRequestDto room2 = new RoomRequestDto("Living", 3.0, 3.0);
        roomsRequest.add(room1);
        roomsRequest.add(room2);
        return new HouseRequestDto(
                " Mi nueva casa",
                "Recoleta",
                roomsRequest);
    }
}
