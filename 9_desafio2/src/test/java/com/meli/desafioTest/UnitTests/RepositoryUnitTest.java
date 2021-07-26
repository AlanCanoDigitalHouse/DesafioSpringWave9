package com.meli.desafioTest.UnitTests;

import com.meli.desafioTest.Dtos.DistrictDTO;
import com.meli.desafioTest.repository.IRepository;
import com.meli.desafioTest.repository.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class RepositoryUnitTest {

    final IRepository repo = new Repository();
    //Valida que barrio el distrito correctamente


    @Test
    @DisplayName("Found District by Name")
    void foundDistrict() {
        //arrange
        String districtName = "Aguada";

        //act
        DistrictDTO current = repo.getDistrictByName(districtName);

        //assert
        Assertions.assertEquals(districtName, current.getDistrict_name());
    }

    //Valida que devuelva null cuando no encuentra el barrio
    @Test
    @DisplayName("Not Found District by Name")
    void notFoundDistrict() {
        //arrange
        String districtName = "";

        //act
        DistrictDTO current = repo.getDistrictByName(districtName);

        //assert
        assertNull(current);
    }
}
