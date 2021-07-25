package com.desafio.tucasitatasaciones.unit;

import com.desafio.tucasitatasaciones.model.District;
import com.desafio.tucasitatasaciones.repository.DistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@DisplayName("Testing district repository")
public class DistrictRepositoryTest {
    DistrictRepository districtRepository = new DistrictRepository();

    @Test
    @DisplayName("District found")
    void foundDistrict(){
        String district = "Alamos Norte Alamos Norte Alamos Norte Alamos";

        Optional<District> response = districtRepository.findDistrictByName(district);

        Assertions.assertAll(
                () -> Assertions.assertTrue(response.isPresent()),
                () -> Assertions.assertEquals(district, response.get().getDistrict_name())
        );
    }

    @Test
    @DisplayName("District not found.")
    void notFoundDistrict(){
        String district = "Alamos norte Alamos Norte Alamos Norte Alamos";

        Optional<District> response = districtRepository.findDistrictByName(district);

        Assertions.assertFalse(response.isPresent());
    }
}
