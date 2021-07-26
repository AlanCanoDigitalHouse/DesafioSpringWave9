package com.bootcamp.desafio2.units.repository;

import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.repositories.implementation.DistrictRepository;
import com.bootcamp.desafio2.utils.TestUtilsGenerator;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class DistrictRepositoryTests  {

    IDistrictRepository districtRepo;

    @BeforeEach
    private void setUp() {
        TestUtilsGenerator.init();

        this.districtRepo = new DistrictRepository();
    }

    @Test
    @DisplayName("District exist with valid parameters")
    public void checkDistrictExistTrue() throws IOException {
        // arrange
        String name = "Belgrano";
        Double price = 1100.0;

        // act
        boolean result = this.districtRepo.districtExist(name,price);

        // assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("District not exist with invalid name")
    public void checkDistrictExistFalse() throws IOException {
        // arrange
        String name = "Villa Urquiza";
        Double price = 1100.0;

        // act
        boolean result = this.districtRepo.districtExist(name,price);

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("District not exist with other price")
    public void checkDistrictExistTrueAndOtherPrice() throws IOException {
        // arrange
        String name = "Belgrano";
        Double price = 200.0;

        // act
        boolean result = this.districtRepo.districtExist(name,price);

        // assert
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("District not exist with NULL parameters")
    public void checkDistrictExistNullParameters() throws IOException {
        // arrange
        String name = null;
        Double price = null;

        // act
        boolean result = this.districtRepo.districtExist(name,price);

        // assert
        Assertions.assertFalse(result);
    }

}
