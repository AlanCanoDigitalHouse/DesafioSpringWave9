package com.example.desafio2.services;


import com.example.desafio2.dtos.*;
import com.example.desafio2.exceptions.BadRequestException;
import com.example.desafio2.repositories.DistrictRepository;
import com.example.desafio2.utils.TestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HouseServiceTest {


   @Mock
   DistrictRepository districtRepository;
    @InjectMocks
    HouseService houseService;

    @Test
    void getPrice() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();

        //mock
        Mockito.when(districtRepository.getByName(house.getDistrict_name()))
                .thenReturn(new DistrictDTO(house.getDistrict_name(),house.getDistrict_price()));

        //act
        HouseDetailResponseDto houseResponse = houseService.getPrice(house);

        //assert
        assertEquals(24000,houseResponse.getPrice());
        assertEquals(house.getProp_name(),houseResponse.getName() );
        assertEquals(house.getDistrict_name(),houseResponse.getDistrict_name() );
        assertEquals(house.getDistrict_price(),houseResponse.getDistrict_price());
        assertEquals(house.getEnvironments().size(),houseResponse.getEnvironment().size());
    }
    @Test
    void validateDistrict(){
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        house.setDistrict_name("asd");
        //mock
        Mockito.when(districtRepository.getByName(house.getDistrict_name()))
                .thenThrow(new BadRequestException("District not found"));

        //assert-act
        assertThrows(BadRequestException.class,()->houseService.getPrice(house));
    }

    @Test
    void getSquareMeters() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        //(act)    2*4+10*6+3*4 <- current square meters
        HouseResponseDTO houseResponse = new HouseResponseDTO(house.getProp_name(), 80.0);
        //assert
        Assertions.assertEquals(houseResponse,houseService.getSquareMeters(house));
    }

    @Test
    void getBiggerEnv() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        EnvDTO env = new EnvDTO("Habitacion Matrimonial",
                10, 6);
        //act
        EnvDTO envResponse = houseService.getBiggerEnv(house);
        //assert
        assertEquals(env,envResponse);
    }
    @Test
    void getBiggerEnvWithoutEnvs(){
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        house.setEnvironments(new ArrayList<>());
        //act-assert
        assertThrows(BadRequestException.class, ()->houseService.getBiggerEnv(house));
    }

    @Test
    void getListEnv() {
        //arrange
        HouseDTO house = TestGenerator.generateHouse();
        //mock
        Mockito.when(districtRepository.getByName(house.getDistrict_name()))
                .thenReturn(new DistrictDTO("Sur",300));
        //act
        List<EnvResponseDto> list = houseService.getListEnv(house);
        //assert
        for (int i = 0; i < list.size(); i++) {
            EnvDTO env = house.getEnvironments().get(i);
            assertEquals(list.get(i).getName(),env.getEnvironment_name());
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