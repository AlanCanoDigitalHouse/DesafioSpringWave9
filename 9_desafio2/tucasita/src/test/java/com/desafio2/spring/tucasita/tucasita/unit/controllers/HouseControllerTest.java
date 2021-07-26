package com.desafio2.spring.tucasita.tucasita.unit.controllers;

import com.desafio2.spring.tucasita.tucasita.controllers.HouseController;
import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseBigRoomDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseRoomsDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseSizeDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseValueDTO;
import com.desafio2.spring.tucasita.tucasita.exceptions.ServiceExceptions;
import com.desafio2.spring.tucasita.tucasita.services.IHouseService;
import com.desafio2.spring.tucasita.tucasita.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Test - House Controller")
public class HouseControllerTest {

    @Mock
    IHouseService service;

    @InjectMocks
    HouseController controller;

    @Test
    @DisplayName("Correct size")
    void correctSizeTest() throws ServiceExceptions {
        //arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        HouseSizeDTO expectedSize = new HouseSizeDTO(33.0);
        when(service.getHouseSize(house)).thenReturn(expectedSize);

        //act
        ResponseEntity responseEntity = controller.getHouseSize(house);

        //assert
        verify(service, atLeastOnce()).getHouseSize(house);
        Assertions.assertEquals(expectedSize, responseEntity.getBody());
    }

    /** House Value Tests */
    @Test
    @DisplayName("Correct Price")
    void correctPalermoPriceTest() throws ServiceExceptions {
        //arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        HouseValueDTO expectedValue = new HouseValueDTO(33.0 * 1000);
        when(service.getHouseValue(house)).thenReturn(expectedValue);

        //act
        ResponseEntity actualValue = controller.getHouseValue(house);

        //assert
        verify(service, atLeastOnce()).getHouseValue(house);
        Assertions.assertEquals(expectedValue, actualValue.getBody());
    }

    /** House Biggest room test*/
    @Test
    @DisplayName("House Biggest Room Test - Correct room")
    void correctRoomTest() throws ServiceExceptions {
        //  arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        HouseBigRoomDTO expectedValue = new HouseBigRoomDTO("Living");
        when(service.getHouseBigRoom(house)).thenReturn(expectedValue);

        //act
        ResponseEntity actualValue = controller.getBiggestRoom(house);

        //assert
        verify(service, atLeastOnce()).getHouseBigRoom(house);
        Assertions.assertEquals(expectedValue, actualValue.getBody());
    }

    /** House rooms size list test*/
    @Test
    @DisplayName("Correct list")
    void correctRoomsSizeTest() throws ServiceExceptions {
        //  arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        Map<String, Double> expectedList = new HashMap<>();
        expectedList.put("Cocina", 2.3 * 3.0);
        expectedList.put("Living", 4.3 * 3.0);
        expectedList.put("Dormitorio", 3.3 * 4.0);
        HouseRoomsDTO expectedValue = new HouseRoomsDTO(expectedList);
        when(service.getHouseRooms(house)).thenReturn(expectedValue);

        //act
        ResponseEntity actualValue = controller.getRoomsMts(house);

        //assert
        verify(service, atLeastOnce()).getHouseRooms(house);
        Assertions.assertEquals(expectedValue, actualValue.getBody());
    }
}
