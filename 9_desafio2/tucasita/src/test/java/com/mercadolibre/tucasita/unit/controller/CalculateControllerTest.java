package com.mercadolibre.tucasita.unit.controller;

import com.mercadolibre.tucasita.controller.CalculateController;
import com.mercadolibre.tucasita.domain.House;
import com.mercadolibre.tucasita.dto.RoomDTO;
import com.mercadolibre.tucasita.service.HouseCalculateService;
import com.mercadolibre.tucasita.util.TestDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateControllerTest {

    @Mock
    private HouseCalculateService houseCalculateService;

    @InjectMocks
    private CalculateController calculateController;

    @Test
    @DisplayName("Test Calcular Total Metros Cuadrados")
    public void testCalculateHouseTotalSquareMeters() {

        House house = TestDataGenerator.getValidHouse();
        double expectedTotalSquareMeter = 413.0;

        when(houseCalculateService.calculateTotalSquareMeters(house)).thenReturn(expectedTotalSquareMeter);

        double totalSquareMeters = calculateController.calculateTotalSquareMeters(house);

        verify(houseCalculateService, atLeastOnce()).calculateTotalSquareMeters(house);

        assertEquals(expectedTotalSquareMeter,totalSquareMeters);
    }

    @Test
    @DisplayName("Test Calcular Precio con Input Valido")
    public void testCalculatePriceWithValidDistrict() {

        House house = TestDataGenerator.getValidHouse();
        double expectedHousePrice = 826000;

        when(houseCalculateService.calculateHousePrice(house)).thenReturn(expectedHousePrice);

        double housePrice = calculateController.calculateHousePrice(house);

        verify(houseCalculateService, atLeastOnce()).calculateHousePrice(house);

        assertEquals(expectedHousePrice, housePrice);
    }

    @Test
    @DisplayName("Test Calcular el Precio de la Casa con Input Invalido.")
    public void testCalculatePriceWithInvalidInput() {

        House house = TestDataGenerator.getInvalidHouseByDistrictName();

        ResponseStatusException expectedException = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid district input.");

        when(houseCalculateService.calculateHousePrice(house)).thenThrow(expectedException);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> calculateController.calculateHousePrice(house));

        verify(houseCalculateService, atLeastOnce()).calculateHousePrice(house);

        assertAll(
                () -> assertEquals(expectedException.getMessage(), ex.getMessage()),
                () -> assertEquals(expectedException.getStatus(), ex.getStatus())
        );
    }

    @Test
    @DisplayName("Test Encontrar la Habitacion mas Grande")
    public void testFindBiggestRoom() {

        House house = TestDataGenerator.getValidHouse();

        RoomDTO expectedRoom = TestDataGenerator.getBiggestRoomOfValidHouse();

        when(houseCalculateService.findBiggestRoom(house)).thenReturn(expectedRoom);

        RoomDTO biggestRoom = calculateController.findBiggestRoom(house);

        verify(houseCalculateService, atLeastOnce()).findBiggestRoom(house);

        assertEquals(expectedRoom, biggestRoom);
    }

    @Test
    @DisplayName("Test Calcular el Tamano de las habitaciones")
    public void testCalculateRoomSizes() {

        House house = TestDataGenerator.getValidHouse();
        List<RoomDTO> expectedRoomSizeList = TestDataGenerator.getRoomSizeList();

        when(houseCalculateService.calculateRoomSizes(house)).thenReturn(expectedRoomSizeList);

        List<RoomDTO> roomSizeList = calculateController.calculateRoomSizes(house);

        verify(houseCalculateService, atLeastOnce()).calculateRoomSizes(house);

        assertEquals(expectedRoomSizeList, roomSizeList);
    }

}
