package com.example.desafio2.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistrictGeneratorTest {
    @Test
    void getAllDistrict() {
        Assertions.assertEquals(4,DistrictGenerator.getDistrictFile("district").size());
    }

    @Test
    void getAllDistrictWithWrongName(){
        Assertions.assertThrows(RuntimeException.class,()->DistrictGenerator.getDistrictFile("idontknow"));
    }
}