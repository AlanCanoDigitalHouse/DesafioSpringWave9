package com.mercadolibre.tucasitatasaciones.unit.controllers;

import com.mercadolibre.tucasitatasaciones.controllers.HouseController;
import com.mercadolibre.tucasitatasaciones.dtos.request.HouseDTO;
import com.mercadolibre.tucasitatasaciones.dtos.response.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.services.IHouseService;
import com.mercadolibre.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HouseControllerTest {

    @Mock
    IHouseService iHouseService;

    @InjectMocks
    HouseController houseController;

    @Test
    @DisplayName("Well calculated square meters")
    void houseWellCalculatedSquareMeters() throws DistrictNotFoundException {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.getSquareMetersWith3Rooms();

        when(iHouseService.assessmentSquareMeters(housePayload)).thenReturn(response);

        ResponseEntity<AssessmentDTO> realResponse = houseController.calculateSquareMeters(housePayload);

        verify(iHouseService, times(1)).assessmentSquareMeters(any(HouseDTO.class));

        assertAll(() -> assertEquals(response, realResponse.getBody()), () -> assertEquals(200, realResponse.getStatusCodeValue()));
    }

    @Test
    @DisplayName("Well calculated price")
    void houseWellCalculatedPrice() throws DistrictNotFoundException {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.getPriceWith3Rooms();

        when(iHouseService.assessmentPrice(housePayload)).thenReturn(response);

        ResponseEntity<AssessmentDTO> realResponse = houseController.calculatePrice(housePayload);

        verify(iHouseService, times(1)).assessmentPrice(any(HouseDTO.class));

        assertAll(() -> assertEquals(response, realResponse.getBody()), () -> assertEquals(200, realResponse.getStatusCodeValue()));
    }

    @Test
    @DisplayName("Well calculated biggest room")
    void houseWellCalculatedBiggestRoom() throws DistrictNotFoundException {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.getBiggestRoomWith3Rooms();

        when(iHouseService.assessmentBiggestRoom(housePayload)).thenReturn(response);

        ResponseEntity<AssessmentDTO> realResponse = houseController.calculateBiggestRoom(housePayload);

        verify(iHouseService, times(1)).assessmentBiggestRoom(any(HouseDTO.class));

        assertAll(() -> assertEquals(response, realResponse.getBody()), () -> assertEquals(200, realResponse.getStatusCodeValue()));
    }

    @Test
    @DisplayName("Well calculated square meters of rooms")
    void houseWellCalculatedSquareMetersRooms() throws DistrictNotFoundException {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.get3RoomsWithSquareMeters();

        when(iHouseService.assessmentSquareMetersEachRoom(housePayload)).thenReturn(response);

        ResponseEntity<AssessmentDTO> realResponse = houseController.calculateSquareMetersEachRoom(housePayload);

        verify(iHouseService, times(1)).assessmentSquareMetersEachRoom(any(HouseDTO.class));

        assertAll(() -> assertEquals(response, realResponse.getBody()), () -> assertEquals(200, realResponse.getStatusCodeValue()));
    }

}
