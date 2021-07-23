package com.example.desafio2.repositories;

import com.example.desafio2.dtos.DistrictDTO;
import com.example.desafio2.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistrictRepositoryTest {
    DistrictRepository districtRepository = new DistrictRepository();
    @Test
    void checkGetByName(){
        assertEquals(districtRepository.getByName("Sur"),new DistrictDTO("Sur",300));
        assertEquals(districtRepository.getByName("Norte"),new DistrictDTO("Norte",400));
        assertEquals(districtRepository.getByName("Este"),new DistrictDTO("Este",300));
        assertEquals(districtRepository.getByName("Oeste"),new DistrictDTO("Oeste",100));
    }

    @Test
    void getByWrongName(){
        String name = "Nor-oeste";
        assertThrows(BadRequestException.class,()->districtRepository.getByName(name));
    }
}