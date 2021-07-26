package com.bootcamp.desafio2.repository;


import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.repositories.implementation.DistrictRepository;
import com.bootcamp.desafio2.util.TestUtilGenerator;
import org.junit.jupiter.api.*;


import java.io.IOException;


public class DistrictRepositoryTest {

    DistrictRepository repository;

    @BeforeEach
    private void setUp() {
        TestUtilGenerator.emptyPricesFile();
        this.repository = new DistrictRepository();
        TestUtilGenerator.appendNewDistrict(new DistrictDto("Narvarte", 1000.0));
    }


    @Test
    @DisplayName("Find District on DataBase")
    void findDistrict () throws IOException {

        Boolean exists = repository.districtExist("Narvarte", 1000.0);

        Assertions.assertEquals(true, exists);
    }

    @Test
    @DisplayName("Find District on DataBase")
    void notFindDistrict () throws IOException {

        DistrictDto districtDto = new DistrictDto("Roma", 1000.0);
        Boolean exists = repository.districtExist(districtDto.getDistrictName(), districtDto.getDistrictPrice());

        Assertions.assertEquals(false, exists);
    }


}
