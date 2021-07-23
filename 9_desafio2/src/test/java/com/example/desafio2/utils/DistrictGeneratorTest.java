package com.example.desafio2.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistrictGeneratorTest {

    @Test
    void getAllDistrict() {
        assertEquals(4,DistrictGenerator.getAllDistrict().size());
    }
}