package com.squareMeter.unit.repository;

import com.squareMeter.exception.exception.DistrictAlreadyExistsException;
import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.DistrictModel;
import com.squareMeter.repository.DistrictRepository;
import com.squareMeter.utils.RunAtStart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

public class DistrictRepositoryTest {
    DistrictRepository repo = new DistrictRepository();

    @BeforeEach
    public void resetDB(){
        RunAtStart.refresh();
    }
    @Test
    @DisplayName("Insert data into de db")
    public void insertData(){
        DistrictModel model = DistrictModel.builder().district_name("Rejalo").district_price(1000.0).build();
        try {
            repo.insert(model);
            Assertions.assertTrue(true);
        } catch (DistrictAlreadyExistsException e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Find by name in the db")
    public void findByName(){
        try {
            DistrictModel model = repo.findDistrictByName("Galicia");
            Assertions.assertEquals("Galicia",model.getDistrict_name());
        } catch (DistrictNotExistsException e) {
            Assertions.fail();
        }
    }
    @Test
    @DisplayName("Find a name that not exists")
    public void findByBadName(){
        Assertions.assertThrows(DistrictNotExistsException.class, ()->repo.findDistrictByName("fffffalicia"));
    }

}
