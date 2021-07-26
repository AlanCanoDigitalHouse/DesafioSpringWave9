package com.example.desafiotesting.units.repositories;

import com.example.desafiotesting.dto.PriceDTO;
import com.example.desafiotesting.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceRepositoryTest {

    PriceRepositoryImpl repository;

    @BeforeEach
    void init() {
        this.repository = new PriceRepositoryImpl();
    }

    @Test
    void notFindLocation() {
        //assert
        Assertions.assertThrows(
                Exception.class,
                () ->  repository.findPriceLocation(null)
        );
    }

    @Test
    void FindPriceLocation() {
        PriceDTO actualFindPriceLocationResult = this.repository.findPriceLocation("Palermo");
        assertEquals("Palermo", actualFindPriceLocationResult.getDistrict_name());
        assertEquals(1000, actualFindPriceLocationResult.getDistrict_price().intValue());
    }

}