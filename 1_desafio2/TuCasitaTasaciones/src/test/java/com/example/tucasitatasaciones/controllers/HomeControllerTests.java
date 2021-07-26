package com.example.tucasitatasaciones.controllers;

import com.example.tucasitatasaciones.dtos.requests.HomeRequestDto;
import com.example.tucasitatasaciones.services.IHomeService;
import com.example.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTests {
    @Mock
    IHomeService homeService;

    @InjectMocks
    HomeController homeController;

    @Test
    public void totalMetersTest(){

        //arrange
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home 1");

        //act
        Objects.requireNonNull(homeController.totalMeters(home).getBody()).getMeter();

        //assert
        verify(homeService, atLeastOnce()).calculateMeters(home);
    }

    @Test
    public void biggestRoomTest(){
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home 1");

        homeController.biggestRoom(home).getBody();

        verify(homeService, atLeastOnce()).findBiggestRoom(home);
    }

    @Test
    public void metersPerRoom(){
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home 1");

        homeController.metersPerRoom(home);

        verify(homeService, atLeastOnce()).calculateMetersPerRoom(home);
    }


}
