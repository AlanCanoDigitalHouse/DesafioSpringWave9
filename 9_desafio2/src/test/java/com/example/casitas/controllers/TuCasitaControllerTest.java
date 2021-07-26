package com.example.casitas.controllers;

import com.example.casitas.dtos.HouseDTO;
import com.example.casitas.exceptions.DistrictNotFoundException;
import com.example.casitas.services.HouseService;
import com.example.casitas.utils.HouseTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TuCasitaControllerTest {

    @Mock
    HouseService service;

    @InjectMocks
    TuCasitaController controller;

    @Test
    public void squareMeters() {
        HouseDTO house = HouseTestUtils.buildHouse();

        controller.getSquareMeters(house);

        Mockito.verify(service, Mockito.atLeastOnce()).getSquareMeters(house);
    }

    @Test
    public void getPrice() throws DistrictNotFoundException {
        HouseDTO house = HouseTestUtils.buildHouse();

        controller.getPrice(house);

        Mockito.verify(service, Mockito.atLeastOnce()).getPrice(house);
    }

    @Test
    public void getBiggerEnvironment() {
        HouseDTO house = HouseTestUtils.buildHouse();

        controller.getBiggerEnvironment(house);

        Mockito.verify(service, Mockito.atLeastOnce()).getBiggerEnvironment(house);
    }

    @Test
    public void getSquareMetersEnvironments() {
        HouseDTO house = HouseTestUtils.buildHouse();

        controller.getSquareMetersEnvironments(house);

        Mockito.verify(service, Mockito.atLeastOnce()).getListEnvironments(house);
    }


}
