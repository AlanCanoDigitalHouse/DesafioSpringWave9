package com.example.casitas.repositories;

import com.example.casitas.exceptions.DistrictNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistrictRepositoryTest {

    DistrictRepository districtRepository = new DistrictRepositoryImpl();

    @Test
    void findDistrictName() {
        String name = null;

        try {
            name = districtRepository.findDistrictByName("Palermo");
        } catch (DistrictNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("Palermo", name);
    }

    @Test
    void notFoundDistrictName() {
        String name = "Recoleta";

        Assertions.assertThrows(DistrictNotFoundException.class, () -> districtRepository.findDistrictByName(name));
    }
}
