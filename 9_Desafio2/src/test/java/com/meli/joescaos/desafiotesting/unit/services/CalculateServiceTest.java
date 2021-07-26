package com.meli.joescaos.desafiotesting.unit.services;

import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.HouseResponseDTO;
import com.meli.joescaos.desafiotesting.dto.PriceDTO;
import com.meli.joescaos.desafiotesting.dto.RoomDTO;
import com.meli.joescaos.desafiotesting.exceptions.DistrictNotFoundException;
import com.meli.joescaos.desafiotesting.repositories.PriceRepository;
import com.meli.joescaos.desafiotesting.services.Impl.CalculateServiceImpl;
import com.meli.joescaos.desafiotesting.utilsTest.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayName("Calculate Services Tests")
public class CalculateServiceTest {

    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    CalculateServiceImpl calculateService;


    private HouseResponseDTO settingMocks() {
        HouseDTO house = TestUtilsGenerator.createHouse();
        PriceDTO priceDTOMock = TestUtilsGenerator.createPriceDTO();
        Mockito.when(priceRepository.districtExists(house.getDistrict_name())).thenReturn(priceDTOMock);
        Mockito.when(priceRepository.getDistrictPrice(house.getDistrict_name())).thenReturn(priceDTOMock.getPrice());
        return calculateService.calculate(house);
    }

    private void verifyMocks() {
        Mockito.verify(priceRepository, Mockito.atLeastOnce()).districtExists(Mockito.anyString());
        Mockito.verify(priceRepository, Mockito.atLeastOnce()).getDistrictPrice(Mockito.anyString());
    }

    private List<Integer> getIntegerList(HouseResponseDTO response) {
        List<Integer> roomSquaredFeet = new ArrayList<>();
        response.getRooms().stream().forEach(room -> roomSquaredFeet.add(room.getSquareFeet()));
        return roomSquaredFeet;
    }

    @Test
    @DisplayName("Testing that the total of mt2 in a property is right")
    public void checkPropertyTotalSquaredFeetTest(){
        HouseResponseDTO response = settingMocks();
        verifyMocks();
        Integer totalSquaredFeetExpected = 366;
        Assertions.assertEquals(totalSquaredFeetExpected, response.getSquareFeet());
    }

    @Test
    @DisplayName("Checking biggest Room area")
    public void checkBiggestRoomInPropertyTest(){
        HouseDTO house = TestUtilsGenerator.createHouse();
        HouseResponseDTO response = settingMocks();
        RoomDTO biggestExpected = house.getRooms().get(1);
        verifyMocks();
        Assertions.assertEquals(biggestExpected, response.getBiggest());
    }

    @Test
    @DisplayName("Check mt2 per environment test")
    public void checkSquaredMetersForEnvironmentTest(){
        HouseResponseDTO response = settingMocks();
        Mockito.verify(priceRepository, Mockito.atLeastOnce()).getDistrictPrice(Mockito.anyString());
        List<Integer> roomSquaredFeetExpected = Arrays.asList(new Integer[]{150, 216});
        List<Integer> roomSquaredFeet = getIntegerList(response);
        Assertions.assertEquals(roomSquaredFeetExpected, roomSquaredFeet);

    }

}
