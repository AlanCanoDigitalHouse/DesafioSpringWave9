package com.example.tucasita.unit;

import com.example.tucasita.DTO.request.HouseRequestDTO;
import com.example.tucasita.DTO.response.HouseResponseDTO;
import com.example.tucasita.TestingUtils;
import com.example.tucasita.repositories.interfaces.DistrictRepository;
import com.example.tucasita.services.implementations.HouseServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseServiceImplTest {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    HouseServiceImpl houseService;

    @Test
    @DisplayName("Valid House")
    void calculateForHouse() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        when(districtRepository.getPriceOrThrowException("Sur")).thenReturn(300.0);
        //act
        houseService.calculateForHouse(hrDTO);

        //assert
        verify(districtRepository, atLeastOnce()).getPriceOrThrowException(hrDTO.getDistrictName());
    }

    @Test
    @DisplayName("Total Square M")
    void testTotalSquareM() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        when(districtRepository.getPriceOrThrowException("Sur")).thenReturn(300.0);

        //act
        HouseResponseDTO hrsDTO = houseService.calculateForHouse(hrDTO);

        //assert
        assertEquals(20, hrsDTO.getTotalSquareM());
    }

    @Test
    @DisplayName("House Value")
    void testHouseValue() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        when(districtRepository.getPriceOrThrowException("Sur")).thenReturn(300.0);

        //act
        HouseResponseDTO hrsDTO = houseService.calculateForHouse(hrDTO);

        //assert
        assertEquals(6000, hrsDTO.getHouseValue());
    }

    @Test
    @DisplayName("Room Sizes")
    void testRoomSizes() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        Map<String, Double> roomSizes = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            roomSizes.put("Room " + i, (double) ((i + 1) * (i + 2)));
        }
        when(districtRepository.getPriceOrThrowException("Sur")).thenReturn(300.0);

        //act
        HouseResponseDTO hrsDTO = houseService.calculateForHouse(hrDTO);

        //assert
        assertEquals(roomSizes, hrsDTO.getRoomSizes());
    }

    @Test
    @DisplayName("Biggest Room Name")
    void testMaxRoomSize() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        Map<String, Double> roomSizes = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            roomSizes.put("Room " + i, (double) ((i + 1) * (i + 2)));
        }
        when(districtRepository.getPriceOrThrowException("Sur")).thenReturn(300.0);

        //act
        HouseResponseDTO hrsDTO = houseService.calculateForHouse(hrDTO);

        //assert
        assertEquals("Room 2", hrsDTO.getMaxSizeRoomName());
    }

    @Test
    @DisplayName("Price Different In DB")
    void testPriceDifferentInDB() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        when(districtRepository.getPriceOrThrowException("Sur")).thenReturn(301.0);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> houseService.calculateForHouse(hrDTO));
    }

    @Test
    @DisplayName("No district in DB")
    void testDistrictNotInDB() {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        when(districtRepository.getPriceOrThrowException("Sur")).thenThrow(new NoSuchElementException());

        //act & assert
        assertThrows(NoSuchElementException.class, () -> houseService.calculateForHouse(hrDTO));
    }

}