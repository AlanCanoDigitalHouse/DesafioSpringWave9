package com.example.demo.unit.services;

import com.example.demo.dtos.generals.PriceDto;
import com.example.demo.dtos.request.HouseRequestDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;
import com.example.demo.exceptions.DistrictNotFound;
import com.example.demo.repositories.HouseRepository;
import com.example.demo.repositories.HouseRepositoryImpl;
import com.example.demo.services.HouseService;
import com.example.demo.util.TestUtilGenerator;
import org.apache.catalina.startup.HomesUserDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {

    @Mock
    HouseRepository repository;

    @InjectMocks
    HouseService service;

    @Test
    @DisplayName("Validate the house size response from service")
    public void verifyHouseSize() throws DistrictNotFound {
        /*TODO: Validate if the house area is correct*/
        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();
        PriceDto price = new PriceDto(house.getDistrict_name(), house.getDistrict_price());
        when(repository.searchDistrict(house.getDistrict_name())).thenReturn(price);

        // act
        service.getHouseSize(house);

        // assert
        verify(repository, atLeastOnce()).getHouseSize(house.getRooms());

    }

    @Test
    @DisplayName("Validate the house value response from service")
    public void verifyHouseValue() throws DistrictNotFound {
        /*TODO: Validate if the house value is correct*/
        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();
        PriceDto price = new PriceDto(house.getDistrict_name(), house.getDistrict_price());
        when(repository.searchDistrict(house.getDistrict_name())).thenReturn(price);

        // act
        service.getHouseValue(house);

        // assert
        verify(repository, atLeastOnce()).getHouseSize(house.getRooms());
    }

    @Test
    @DisplayName("Validate the biggest room from service")
    public void verifyBiggestRoom() throws DistrictNotFound {
        /*TODO: Validate if biggest room is correct*/
        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();
        PriceDto price = new PriceDto(house.getDistrict_name(), house.getDistrict_price());
        when(repository.searchDistrict(house.getDistrict_name())).thenReturn(price);

        // act
        service.biggestRoom(house);

        // assert
        verify(repository, atLeastOnce()).biggestRoom(house.getRooms());
    }

    @Test
    @DisplayName("Validate the area by room from service")
    public void verifyAreaByRoom() throws DistrictNotFound {
        /*TODO: Validate if the area by room is correct*/
        // arrange
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();
        PriceDto price = new PriceDto(house.getDistrict_name(), house.getDistrict_price());
        when(repository.searchDistrict(house.getDistrict_name())).thenReturn(price);

        // act
        service.roomsArea(house);

        // assert
        verify(repository, atLeastOnce()).roomsArea(house.getRooms());
    }

    @Test
    @DisplayName("Validate not found district exception from service")
    /*TODO: Validate if a district is not found, throw the correct exception*/
    public void validateNotFoundDistrict() throws DistrictNotFound {
        // arrange
        HouseRequestDto invalidDistrict = TestUtilGenerator.createInvalidHouseWithThreeRooms();

        // assert
        assertAll(
                () -> assertThrows(DistrictNotFound.class, () -> {
                    service.roomsArea(invalidDistrict);
                }),
                () -> assertThrows(DistrictNotFound.class, () -> {
                    service.biggestRoom(invalidDistrict);
                }),
                () -> assertThrows(DistrictNotFound.class, () -> {
                    service.getHouseValue(invalidDistrict);
                }),
                () -> assertThrows(DistrictNotFound.class, () -> {
                    service.getHouseSize(invalidDistrict);
                })
        );
    }
}
