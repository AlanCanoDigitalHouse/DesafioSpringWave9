package com.desafio2.spring.tucasita.tucasita.unit.repository;

import com.desafio2.spring.tucasita.tucasita.dtos.request.DistrictDTO;
import com.desafio2.spring.tucasita.tucasita.repositories.PriceLocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit Tet - Price Location Repository")
public class RepositoryTest {

    PriceLocationRepository repo = new PriceLocationRepository();

    @Test
    @DisplayName("Found District by name")
    void findDistrictTest() {
        //arrange
        String disctrictName = "Palermo";
        DistrictDTO expected = new DistrictDTO("Palermo", 1000);

        //act
        DistrictDTO current = repo.findPriceLocation(disctrictName);

        //assert
        Assertions.assertEquals(expected, current);
    }


    @Test
    @DisplayName("Non existing district")
    void NotFoundDistrictTest() {
        //arrange
        String districtName = "notFound";

        //act && assert
        Assertions.assertEquals(null, repo.findPriceLocation(districtName));
    }

    @Test
    @DisplayName("Found district by Name - incorrect price")
    void incorrectDistrictPriceTest()  {
        //arrange
        DistrictDTO incorrectDistrict = new DistrictDTO("Palermo", 45);

        //act
        DistrictDTO current = repo.findPriceLocation(incorrectDistrict.getDistrict_name());

        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(incorrectDistrict.getDistrict_name(), current.getDistrict_name()),
                () -> Assertions.assertNotEquals(incorrectDistrict.getDistrict_price(), current.getDistrict_price())
        );
    }


}
