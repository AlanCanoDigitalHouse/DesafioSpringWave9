package com.bootcamp.desafio2.unit.repositories;

import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.repositories.implementation.DistrictRepository;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DistrictRepositoryTest {

    DistrictRepository districtRepository;

    @BeforeEach @AfterEach
    void init() {
        districtRepository = new DistrictRepository();
        TestUtilsGenerator.initDataBase();
        TestUtilsGenerator.appendNewDistrict(new District("Boita", 3000D));
    }

    @Test
    void districtExistTest() throws IOException {
        String name = "Boita";
        Double price = 3000D;

        boolean response = districtRepository.districtExist(name, price);

        assertTrue(response);
    }


    @Test
    void districtNotExistTest() throws IOException {
        String name = "Boita";
        Double price = 3100D;

        boolean response = districtRepository.districtExist(name, price);

        assertFalse(response);
    }

    @Test
    void districExistWithNameNullTest() throws IOException {
        Double price = 3000D;

        boolean response = districtRepository.districtExist(null, price);

        assertFalse(response);
    }


    @Test
    void districExistWithPriceNullTest() throws IOException {
        String name = "Boita";

        boolean response = districtRepository.districtExist(name, null);

        assertFalse(response);
    }


}
