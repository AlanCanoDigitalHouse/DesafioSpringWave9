package com.example.desafio2.services;


import com.example.desafio2.dtos.EnvDTO;
import com.example.desafio2.dtos.EnvResponseDto;
import com.example.desafio2.dtos.HouseDTO;
import com.example.desafio2.dtos.HouseResponseDTO;
import com.example.desafio2.utils.TestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseServiceTest {


    @Test
    void getPrice() {
        var house = TestGenerator.generateHouse();
        HouseService houseService = new HouseService();

        var houseResponse = houseService.getPrice(house);
        //(2*4 + 10*6 + 3*4)*60
        Assertions.assertEquals(houseResponse.getPrice(), 4800);
        Assertions.assertEquals(houseResponse.getName(), house.getProp_name());
        Assertions.assertEquals(houseResponse.getDistrict_name(), house.getDistrict_name());
        Assertions.assertEquals(houseResponse.getDistrict_price(), house.getDistrict_price());
        Assertions.assertEquals(houseResponse.getEnvironment().size(), house.getEnvironments().size());
    }

    @Test
    void getSquareMeters() {
        var house = TestGenerator.generateHouse();
        HouseService houseService = new HouseService();
        //2*4+10*6+3*4 <- current square meters
        var houseResponse = new HouseResponseDTO(house.getProp_name(), 80.0);

        Assertions.assertEquals(houseService.getSquareMeters(house), houseResponse);
    }

    @Test
    void getBiggerEnv() {
        var house = TestGenerator.generateHouse();
        HouseService houseService = new HouseService();

        var envResponse = houseService.getBiggerEnv(house);
        var env = new EnvDTO("Habitacion Matrimonial", 10, 6);

        assertEquals(envResponse, env);
    }

    @Test
    void getListEnv() {
        HouseDTO house = TestGenerator.generateHouse();
        HouseService houseService = new HouseService();

        List<EnvResponseDto> list = houseService.getListEnv(house);
        for (int i = 0; i < list.size(); i++) {
            EnvDTO env = house.getEnvironments().get(i);
            assertEquals(list.get(i).getPrice(),
                    env.getEnvironment_length() *
                            env.getEnvironment_width() *
                            house.getDistrict_price());
            assertEquals(list.get(i).getSquareMeters(),
                    env.getEnvironment_length() *
                            env.getEnvironment_width());
        }
    }
}