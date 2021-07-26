package com.example.demo.unit.repositories;

import com.example.demo.dtos.generals.PriceDto;
import com.example.demo.dtos.request.RoomRequestDto;
import com.example.demo.dtos.response.BiggestRoomResponseDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;
import com.example.demo.exceptions.DistrictNotFound;
import com.example.demo.repositories.HouseRepository;
import com.example.demo.repositories.HouseRepositoryImpl;
import com.example.demo.util.TestUtilGenerator;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseRepositoryTest {

    HouseRepositoryImpl houseRepository = new HouseRepository();

    @Test
    @DisplayName("Test if district exists or doesn't")
    /*TODO: Validate if a district is found, return the correct value*/
    public void validateFoundDistrict() throws DistrictNotFound {
        // arrange
        PriceDto price = TestUtilGenerator.createValidPriceDtoTest();

        // act
        PriceDto foundPrice = null;
        foundPrice = houseRepository.searchDistrict("Recoleta");

        //assert
        assertEquals(price.getDistrict_name(), foundPrice.getDistrict_name());

    }

    @Test
    @DisplayName("Validate not found district from repository")
    /*TODO: Validate if a district is not found, throw the correct exception*/
    public void validateNotFoundDistrict() throws DistrictNotFound {
        // arrange
        PriceDto invalidDistrict = TestUtilGenerator.createInvalidPriceDtoTest();
        DistrictNotFound expectedException = new DistrictNotFound();

        // assert
        DistrictNotFound exception = assertThrows(DistrictNotFound.class, () -> {
            houseRepository.searchDistrict(invalidDistrict.getDistrict_name());
        });
        assertEquals(expectedException, exception);
    }

    @Test
    @DisplayName("Validate the correct house size")
    /*TODO: Validate if the house's size is the correct one*/
    public void validateHouseSize() {
        // arrange
        HouseSizeResponseDto expectedSize = TestUtilGenerator.createValidHouseSizeResponse();
        List<RoomRequestDto> rooms = TestUtilGenerator.createListValidRooms();

        // act
        Double actualSize = houseRepository.getHouseSize(rooms);

        //assert
        assertEquals(expectedSize.getArea(), actualSize);
    }

    @Test
    @DisplayName("Validate the correct biggest room")
    /*TODO: Validate if the biggest room is the correct one*/
    public void validateBiggestRoom() {
        // arrange
        RoomRequestDto expectedRoom = TestUtilGenerator.createValidBiggestRoom();
        List<RoomRequestDto> rooms = TestUtilGenerator.createListValidRooms();

        // act
        RoomRequestDto actualSize = houseRepository.biggestRoom(rooms);

        //assert
        assertEquals(expectedRoom, actualSize);
    }

    @Test
    @DisplayName("Validate the correct area by room")
    /*TODO: Validate if the area by room is correct*/
    public void validateAreaByRoom() {
        // arrange
        List<RoomRequestDto> expectedRooms = TestUtilGenerator.createListValidRooms();

        // act
        List<RoomRequestDto> actualRooms = houseRepository.roomsArea(expectedRooms);

        //assert
        assertEquals(expectedRooms, actualRooms);
    }


}
