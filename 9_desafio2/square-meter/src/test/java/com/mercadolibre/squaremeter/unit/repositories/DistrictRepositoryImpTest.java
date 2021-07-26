package com.mercadolibre.squaremeter.unit.repositories;

import com.mercadolibre.squaremeter.dtos.DistrictDTO;
import com.mercadolibre.squaremeter.repository.DistrictRepositoryImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit Tet - District Repository Impl")
class DistrictRepositoryImpTest {
    DistrictRepositoryImp repo = new DistrictRepositoryImp();

    @Test
    @DisplayName("Test repository District - findDistrictByName true")
    void testFindDistrictByName() {
        String name = "Palermo";
        DistrictDTO expected = new DistrictDTO("Palermo");
        Boolean current = repo.findDistrictByName(name);
        Assertions.assertTrue(current);
    }

    @Test
    @DisplayName("Test repository District - findDistrictByName false")
    void testFindDistrictByNameNull() {
        String name = "Palerm";
        Boolean current = repo.findDistrictByName(name);
        Assertions.assertFalse(current);
    }

    @Test
    @DisplayName("Test repository District - findDistrictAll Size")
    void testFindDistrictAll() {
        int expected = 48;
        List<DistrictDTO> current = repo.findDistrictAll();
        Assertions.assertEquals(expected, current.size());
    }
}