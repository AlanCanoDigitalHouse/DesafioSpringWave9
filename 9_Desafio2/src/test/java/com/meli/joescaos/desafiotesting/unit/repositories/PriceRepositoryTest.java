package com.meli.joescaos.desafiotesting.unit.repositories;

import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.PriceDTO;
import com.meli.joescaos.desafiotesting.exceptions.DistrictNotFoundException;
import com.meli.joescaos.desafiotesting.repositories.Impl.PriceRepositoryImpl;
import com.meli.joescaos.desafiotesting.utilsTest.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Unit test for repository of districts")
public class PriceRepositoryTest {

    PriceRepositoryImpl priceRepository = new PriceRepositoryImpl();

    @Test
    @DisplayName("Test for checking if a district exists in the repository")
    public void checkIfDistrictExistsInRepository(){
        //Arrange
        String districtName = "Palermo";
        HouseDTO expected = TestUtilsGenerator.createHouse();

        //Act
        PriceDTO current = priceRepository.districtExists(districtName);

        //Assert
        Assertions.assertEquals(current.getPrice(), expected.getDistrict_price());
    }

    @Test
    @DisplayName("Test for checking if a district not exists in the repository")
    public void checkDistrictNotExistsException(){
        //Arrange && Act
        String districtName = "palermo";

        //Assert
        Assertions.assertThrows(DistrictNotFoundException.class, () -> priceRepository.districtExists(districtName));
    }

    @Test
    @DisplayName("Test for price.json file exists")
    public void testPriceJSONExistingFile(){
        List<PriceDTO> list = TestUtilsGenerator.readFile();
        Assertions.assertTrue(!list.isEmpty());
    }

}
