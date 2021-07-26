package com.desafios.test.unit.repositories;

import com.desafios.test.dtos.DistrictDTO;
import com.desafios.test.repositories.CalculateRepository;
import com.desafios.test.repositories.CalculateRepositoryImpl;
import org.junit.jupiter.api.*;


public class CalculateRepositoryTest {
    CalculateRepository repository = new CalculateRepositoryImpl();

    @Test
    @DisplayName("Repository - Test to find price of district by location that exists")
    public void findPriceDistrictTest() {
        // arrange
        DistrictDTO district = new DistrictDTO("Palermo", 1000.0);

        // act
        DistrictDTO foundDistrict = this.repository.findPriceDistrict("Palermo");

        // assert
        Assertions.assertEquals(district, foundDistrict);
    }

    @Test
    @DisplayName("Repository - Test to find price of district by location that not exists")
    public void findPriceDistrictNotFoundTest() {
        // act
        DistrictDTO foundDistrict = this.repository.findPriceDistrict("Mi casa");

        // assert
        Assertions.assertEquals(null, foundDistrict);
    }
}
