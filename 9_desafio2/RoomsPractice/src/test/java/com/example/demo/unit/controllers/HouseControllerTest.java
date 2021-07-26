package com.example.demo.unit.controllers;

import com.example.demo.controllers.HouseController;
import com.example.demo.dtos.generals.PriceDto;
import com.example.demo.dtos.request.HouseRequestDto;
import com.example.demo.dtos.request.RoomRequestDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;
import com.example.demo.exceptions.DistrictNotFound;
import com.example.demo.repositories.HouseRepositoryImpl;
import com.example.demo.services.HouseService;
import com.example.demo.util.TestUtilGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HouseControllerTest {

    @Mock
    HouseService service;

    @InjectMocks
    HouseController controller;

    @Test
    @DisplayName("Validate the house size response from controller")
    public void verifyHouseSize() throws DistrictNotFound {
        /*TODO: Validate if the house area is correct*/
        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();

        // act
        controller.getHouseSize(house);

        // assert
        verify(service, atLeastOnce()).getHouseSize(house);

    }

    @Test
    @DisplayName("Validate the house value response from controller")
    public void verifyHouseValue() throws DistrictNotFound {
        /*TODO: Validate if the house value is correct*/

        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();

        // act
        controller.getHouseValue(house);

        // assert
        verify(service, atLeastOnce()).getHouseValue(house);

    }

    @Test
    @DisplayName("Validate the biggest room response from controller")
    public void verifyBiggestRoom() throws DistrictNotFound {
        /*TODO: Validate if the biggest room is correct*/

        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();

        // act
        controller.getBiggestRoom(house);

        // assert
        verify(service, atLeastOnce()).biggestRoom(house);

    }

    @Test
    @DisplayName("Validate the area by room from controller")
    public void verifySizeByRoom() throws DistrictNotFound {
        /*TODO: Validate if the area by room is correct*/

        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();

        // act
        controller.getSizeByRoom(house);

        // assert
        verify(service, atLeastOnce()).roomsArea(house);

    }
}
