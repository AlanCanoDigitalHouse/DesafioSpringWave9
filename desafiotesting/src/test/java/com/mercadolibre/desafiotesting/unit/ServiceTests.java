package com.mercadolibre.desafiotesting.unit;

import com.mercadolibre.desafiotesting.dto.HouseResponseDto;
import com.mercadolibre.desafiotesting.dto.RequestHouseDto;
import com.mercadolibre.desafiotesting.dto.RoomDto;
import com.mercadolibre.desafiotesting.exceptions.DistrictException;
import com.mercadolibre.desafiotesting.repositories.DistrictRepository;
import com.mercadolibre.desafiotesting.services.HouseService;
import com.mercadolibre.desafiotesting.services.HouseServicesImpl;
import com.mercadolibre.desafiotesting.utils.HouseTestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    HouseServicesImpl houseService;


    @Test
    @DisplayName("Test total meters House")
    public void testTotalMetersHouse()   {
        RequestHouseDto request = HouseTestUtils.getRequestHouse(3000.0);

        HouseResponseDto houseResponseDto = null;
        try {
            houseResponseDto = houseService.calculate(request);
        } catch (DistrictException e) {
            e.printStackTrace();
        }

        try {
            verify(districtRepository, atLeastOnce()).findDistrictByName(request.getHouseDto().getDistrict_name());
        } catch (DistrictException e) {
            e.printStackTrace();
        }
        assert houseResponseDto != null;
        Assertions.assertEquals(220.0,houseResponseDto.getSquareFeet());

    }

    @Test
    @DisplayName("Test Biggest meters House")
    public void testBiggestHouse()   {
        RequestHouseDto request = HouseTestUtils.getRequestHouse(3000.0);
        RoomDto biggest = new RoomDto("Sala",12.0,10.0);
        HouseResponseDto houseResponseDto = null;
        try {
            houseResponseDto = houseService.calculate(request);

        } catch (DistrictException e) {
            e.printStackTrace();
        }

        try {
            verify(districtRepository, atLeastOnce()).findDistrictByName(request.getHouseDto().getDistrict_name());
        } catch (DistrictException e) {
            e.printStackTrace();
        }
        assert houseResponseDto != null;
        Assertions.assertEquals(biggest,houseResponseDto.getBiggest());

    }

    @Test
    @DisplayName("Test meters room House")
    public void testMetersByRoomHouse()   {
        RequestHouseDto request = HouseTestUtils.getRequestHouse(3000.0);
        HouseResponseDto houseResponseDto = null;
        try {
            houseResponseDto = houseService.calculate(request);

        } catch (DistrictException e) {
            e.printStackTrace();
        }

        try {
            verify(districtRepository, atLeastOnce()).findDistrictByName(request.getHouseDto().getDistrict_name());
        } catch (DistrictException e) {
            e.printStackTrace();
        }
        assert houseResponseDto != null;
        Assertions.assertEquals(100,houseResponseDto.getRooms().get(0).getSquareFeet());

        Assertions.assertEquals(120,houseResponseDto.getRooms().get(1).getSquareFeet());

    }





}
