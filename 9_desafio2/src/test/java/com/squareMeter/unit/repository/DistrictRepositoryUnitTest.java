package com.squareMeter.unit.repository;

import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.DistrictModel;
import com.squareMeter.repository.DistrictRepository;
import com.squareMeter.utils.RunAtStart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
Units test of the district repository

What is tested?
- Find by name
- Error of district not found
 */

public class DistrictRepositoryUnitTest {
    final DistrictRepository repo = new DistrictRepository();

    @BeforeEach
    public void resetDB() {
        RunAtStart.refresh();
    }

    @Test
    @DisplayName("Find by name in the db")
    public void findByName() {
        try {
            DistrictModel model = repo.findDistrictByName("Galicia");
            Assertions.assertEquals("Galicia", model.getDistrict_name());
        } catch (DistrictNotExistsException e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Find a name that not exists")
    public void findByBadName() {
        Assertions.assertThrows(DistrictNotExistsException.class, () -> repo.findDistrictByName("alicia"));
    }

}
