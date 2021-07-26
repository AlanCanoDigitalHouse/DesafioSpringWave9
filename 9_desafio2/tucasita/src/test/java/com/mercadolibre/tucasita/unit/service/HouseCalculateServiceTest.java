package com.mercadolibre.tucasita.unit.service;

import com.mercadolibre.tucasita.domain.District;
import com.mercadolibre.tucasita.domain.House;
import com.mercadolibre.tucasita.dto.RoomDTO;
import com.mercadolibre.tucasita.repository.DistrictRepository;
import com.mercadolibre.tucasita.service.HouseCalculateServiceImpl;
import com.mercadolibre.tucasita.util.TestDataGenerator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HouseCalculateServiceTest {

    @Mock
    private DistrictRepository districtRepository;

    @InjectMocks
    private HouseCalculateServiceImpl houseCalculateService;

    @Test
    @DisplayName("Test Calcular Total Metros Cuadrados")
    public void testCalculateHouseTotalSquareMeters() {

        House house = TestDataGenerator.getValidHouse();

        double totalSquareMeters = houseCalculateService.calculateTotalSquareMeters(house);

        assertEquals(413,totalSquareMeters);
    }

    @Test
    @DisplayName("Test Calcular Precio con Barrio Valido")
    public void testCalculatePriceWithValidDistrict() {

        House house = TestDataGenerator.getValidHouse();

        when(districtRepository.findByName(house.getDistric().getName())).thenReturn(house.getDistric());

        double housePrice = houseCalculateService.calculateHousePrice(house);

        verify(districtRepository, atLeastOnce()).findByName(house.getDistric().getName());

        assertEquals(826000, housePrice);
    }

    @Test
    @DisplayName("Test Calcular Precio con Nombre de Barrio Invalido")
    public void testCalculatePriceWithInvalidDistrictByName() {

        House house = TestDataGenerator.getInvalidHouseByDistrictName();

        when(districtRepository.findByName(house.getDistric().getName())).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> houseCalculateService.calculateHousePrice(house));

        verify(districtRepository, atLeastOnce()).findByName(house.getDistric().getName());

        assertAll(
                () -> assertEquals("400 BAD_REQUEST \"Invalid district input.\"", ex.getMessage()),
                () -> assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus())
        );
    }

    @Test
    @DisplayName("Test Calcular Precio con Nombre de Barrio Invalido")
    public void testCalculatePriceWithInvalidDistrictByPrice() {

        House house = TestDataGenerator.getInvalidHouseByDistrictPrice();

        District validDistrict = TestDataGenerator.getDistrictOfValidHouse();

        ResponseStatusException expectedException = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid district input.");

        when(districtRepository.findByName(house.getDistric().getName())).thenReturn(validDistrict);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> houseCalculateService.calculateHousePrice(house));

        verify(districtRepository, atLeastOnce()).findByName(house.getDistric().getName());

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

        RoomDTO biggestRoom = houseCalculateService.findBiggestRoom(house);

        assertEquals(expectedRoom, biggestRoom);
    }

    @Test
    @DisplayName("Test Calcular el Tamano de las habitaciones")
    public void testCalculateRoomSizes() {

        House house = TestDataGenerator.getValidHouse();
        List<RoomDTO> expectedRoomSizeList = TestDataGenerator.getRoomSizeList();

        List<RoomDTO> roomSizeList = houseCalculateService.calculateRoomSizes(house);

        assertEquals(expectedRoomSizeList, roomSizeList);
    }
}
