package com.example.tucasitatasaciones.services;

import com.example.tucasitatasaciones.dtos.RoomDto;
import com.example.tucasitatasaciones.dtos.requests.HomeRequestDto;
import com.example.tucasitatasaciones.repository.IDistrictRepository;
import com.example.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomeServiceTests {
    @Mock
    IDistrictRepository districtRepository;

    @InjectMocks
    HomeService service;

    @Test
    public void calculateMetersTests(){
        //arrange
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home");

        //act
        Double m = service.calculateMeters(home);

        // assert
        assertEquals(99.0, m);
    }

    @Test
    public void calculatePriceTests(){
        //arrange
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home");
        when(districtRepository.findDistrict(home.getDistrict().getName(),home.getDistrict().getPrice())).thenReturn(home.getDistrict());

        //act
        Double m = service.calculatePrice(home);

        //assert
        verify(districtRepository, atLeastOnce()).findDistrict(home.getDistrict().getName(),home.getDistrict().getPrice());
        assertEquals(99000.0, m);
    }


    @Test
    public void findBiggestRoomTests(){
        //arrange
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home");

        //act
        RoomDto r = service.findBiggestRoom(home);

        //assert
        assertAll(
                () -> assertEquals("Room 1", r.getName()),
                () -> assertEquals(5.0,r.getLength()),
                () -> assertEquals(10.0,r.getWidth())
        );
    }

    @Test
    public void calculateMetersPerRoomTest(){
        //arrange
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home");

        Map<String, Double> rooms = new HashMap<String, Double>();
        rooms.put("Room 1", 50.0);
        rooms.put("Room 2", 25.0);
        rooms.put("Room 3", 24.0);

        //act
        Map<String,Double> roomMeters = service.calculateMetersPerRoom(home);

        //assert
        assertEquals(roomMeters, rooms);
    }
}
