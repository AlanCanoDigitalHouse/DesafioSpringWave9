package com.example.tucasita.unit;

import com.example.tucasita.repositories.implementations.DistrictRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DistrictRepositoryImplTest {

    DistrictRepositoryImpl districtRepository = new DistrictRepositoryImpl();

    @Test
    @DisplayName("Existing District")
    void getPriceForExistingDistrict() {
        //arrange
        Double expectedPrice = 300.0;

        //act
        Double districtPriceForSur = districtRepository.getPriceOrThrowException("Sur");

        //assert
        assertEquals(expectedPrice,districtPriceForSur);
    }

    @Test
    @DisplayName("NonExistingDistrict")
    void getPriceForNonExistingPrice() {
        //act & assert
        assertThrows(NoSuchElementException.class, () -> districtRepository.getPriceOrThrowException("None"));
    }

}