package com.meli.bootcamp.unit.repositories;

import com.meli.bootcamp.dto.DistrictDTO;
import com.meli.bootcamp.exceptions.DistrictException;
import com.meli.bootcamp.repositories.DistrictRepositoryImp;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class DistrictRepositoryTest {


    DistrictRepositoryImp districtRepository;

    @BeforeEach
    @AfterEach
    private void setUp() throws IOException {
        this.districtRepository = new DistrictRepositoryImp();
    }

    @Test
    @DisplayName("Invalid District (Exception) - Repository")
    public void districtNotExits() {
        DistrictDTO expectedDTO = new DistrictDTO("Barrancas", 123.0);
        Assertions.assertThrows(DistrictException.class, () -> districtRepository.validateDistrict(expectedDTO.getDistrict_name()));
    }

    @Test
    @DisplayName("Valid District - Repository")
    public void districtExists() throws DistrictException {

        DistrictDTO expectedDTO = new DistrictDTO("Belgrano", 123.0);
        boolean result = false;
        result = districtRepository.validateDistrict(expectedDTO.getDistrict_name());
        Assertions.assertTrue(result);

    }
}
