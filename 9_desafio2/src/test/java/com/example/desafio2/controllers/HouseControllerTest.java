package com.example.desafio2.controllers;

import com.example.desafio2.dtos.HouseDTO;
import com.example.desafio2.services.HouseService;
import com.example.desafio2.utils.TestGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HouseControllerTest {
    @Mock
    HouseService houseService;

    @InjectMocks
    HouseController houseController;


    @Test
    void getSquareMeters() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        //act
        houseController.getSquareMeters(house);
        //assert
        Mockito.verify(houseService,Mockito.atLeastOnce()).getSquareMeters(house);
    }

    @Test
    void getValueByEnvAndDistrict() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        //act
        houseController.getValueByEnvAndDistrict(house);
        //assert
        Mockito.verify(houseService,Mockito.atLeastOnce()).getPrice(house);
    }

    @Test
    void getBiggerEnv() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        //act
        houseController.getBiggerEnv(house);
        //assert
        Mockito.verify(houseService,Mockito.atLeastOnce()).getBiggerEnv(house);
    }

    @Test
    void getSquareMetersEnv() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        //act
        houseController.getSquareMetersEnv(house);
        //assert
        Mockito.verify(houseService,Mockito.atLeastOnce()).getListEnv(house);
    }
}