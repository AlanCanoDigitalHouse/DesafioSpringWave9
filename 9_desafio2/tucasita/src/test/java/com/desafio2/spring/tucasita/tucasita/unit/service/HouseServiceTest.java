package com.desafio2.spring.tucasita.tucasita.unit.service;

import com.desafio2.spring.tucasita.tucasita.dtos.request.DistrictDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseBigRoomDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseRoomsDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseSizeDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseValueDTO;
import com.desafio2.spring.tucasita.tucasita.exceptions.DistrictDoesNotExistException;
import com.desafio2.spring.tucasita.tucasita.exceptions.ServiceExceptions;
import com.desafio2.spring.tucasita.tucasita.exceptions.WrongPriceException;
import com.desafio2.spring.tucasita.tucasita.repositories.PriceLocationRepository;
import com.desafio2.spring.tucasita.tucasita.services.HouseService;
import com.desafio2.spring.tucasita.tucasita.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Test - House Service")
public class HouseServiceTest {

    @Mock
    PriceLocationRepository mockRepo;

    @InjectMocks
    HouseService houseService;

    /** House Size Tests */
    @Test
    @DisplayName("House Size Tests - District doesn't exist")
    void invalidDistrictHouseSizeTest() {
        //arrange
        HouseDTO houseWithInvalidDistrict = TestUtilGenerator.invalidDistrictNameHouse();
        String invalidDistrictName = houseWithInvalidDistrict.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(invalidDistrictName)).thenReturn(null);

        //act && assert
        Assertions.assertThrows(DistrictDoesNotExistException.class, () -> houseService.getHouseSize(houseWithInvalidDistrict));
    }

    @Test
    @DisplayName("House Size Tests - Wrong district price")
    void invalidDistrictPriceSizeTest() {
        //arrange
        HouseDTO houseWithInvalidDistrict = TestUtilGenerator.invalidDistrictPriceHouse();
        String invalidDistrictName = houseWithInvalidDistrict.getDistrict().getDistrict_name();
        DistrictDTO expectedDistrict = new DistrictDTO("Palermo", 1000);
        when(mockRepo.findPriceLocation(invalidDistrictName)).thenReturn(expectedDistrict);

        //act && assert
        Assertions.assertThrows(WrongPriceException.class,
                () -> houseService.getHouseSize(houseWithInvalidDistrict));
    }

    @Test
    @DisplayName("House Size Tests - Correct size")
    void correctSizeTest() throws ServiceExceptions {
        //arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        String districtName = house.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(districtName)).thenReturn(house.getDistrict());
        double expectedSize = 33.0;

        //act
        HouseSizeDTO houseSizeDTO = houseService.getHouseSize(house);

        //assert
        verify(mockRepo, atLeastOnce()).findPriceLocation(districtName);
        Assertions.assertEquals(expectedSize, houseSizeDTO.getTotalSize());
    }

    /** House Value Tests */
    @Test
    @DisplayName("House Value Tests - Correct Price")
    void correctPalermoPriceTest() throws ServiceExceptions {
        //arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        String districtName = house.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(districtName)).thenReturn(house.getDistrict());
        double expectedValue = 33.0 * 1000;

        //act
        HouseValueDTO houseValueDTO = houseService.getHouseValue(house);

        //assert
        verify(mockRepo, atLeastOnce()).findPriceLocation(districtName);
        Assertions.assertEquals(expectedValue, houseValueDTO.getValue());
    }

    @Test
    @DisplayName("House Value Tests - Correct Price")
    void correctRecoletaPriceTest() throws ServiceExceptions {
        //arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSizeRecoleta();
        String districtName = house.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(districtName)).thenReturn(house.getDistrict());
        double expectedValue = 33.0 * 900;

        //act
        HouseValueDTO houseValueDTO = houseService.getHouseValue(house);

        //assert
        verify(mockRepo, atLeastOnce()).findPriceLocation(districtName);
        Assertions.assertEquals(expectedValue, houseValueDTO.getValue());
    }

    /** House Biggest room test*/
    @Test
    @DisplayName("House Biggest Room Test - Correct room")
    void correctRoomTest() throws ServiceExceptions {
        //  arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        String districtName = house.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(districtName)).thenReturn(house.getDistrict());
        String expectedRoom = "Living";
        //act
        HouseBigRoomDTO houseBigRoomDTO = houseService.getHouseBigRoom(house);

        //assert
        verify(mockRepo, atLeastOnce()).findPriceLocation(districtName);
        Assertions.assertEquals(expectedRoom, houseBigRoomDTO.getName());
    }

    /** House rooms size list test*/
    @Test
    @DisplayName("House Rooms Size Test - Correct list")
    void correctRoomsSizeTest() throws ServiceExceptions {
        //  arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        String districtName = house.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(districtName)).thenReturn(house.getDistrict());
        Map<String, Double> expectedList = new HashMap<>();
        expectedList.put("Cocina", 2.3 * 3.0);
        expectedList.put("Living", 4.3 * 3.0);
        expectedList.put("Dormitorio", 3.3 * 4.0);

        //act
        HouseRoomsDTO houseRoomsDTO = houseService.getHouseRooms(house);

        //assert
        verify(mockRepo, atLeastOnce()).findPriceLocation(districtName);
        Assertions.assertTrue(expectedList.equals(houseRoomsDTO.getRooms()));
    }

}
