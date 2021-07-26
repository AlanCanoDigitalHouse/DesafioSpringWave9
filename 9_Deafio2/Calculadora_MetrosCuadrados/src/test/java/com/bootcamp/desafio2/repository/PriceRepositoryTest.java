package com.bootcamp.desafio2.repository;

import com.bootcamp.desafio2.dtos.request.DistrictDTO;
import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.repositories.implementation.DistrictRepository;
import com.bootcamp.desafio2.services.implementation.DistrictService;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;



public class PriceRepositoryTest {

    IDistrictRepository districtRepository;

    @BeforeEach
    @AfterEach
    private void setUp() {
        TestUtilsGenerator.getPriceFile();
        this.districtRepository = new DistrictRepository();
    }

   @Test
   @DisplayName("Validación repositorio - Busqueda - Distrito existente en BD.")
   void findDistrictExistsTest() throws DistrictNotExistsException, PriceNotMatchException {

       //arrange;
       District district = new District("Belgrano", 1100D);

       //act
       District current = districtRepository.findPriceByLocation(district.getLocation());

       //assert
       Assertions.assertAll(
               () -> Assertions.assertEquals(district.getLocation(), current.getLocation()),
               () -> Assertions.assertEquals(district.getPrice(), current.getPrice())
       );

   }

    @DisplayName("Validación repositorio - Busqueda - Distrito no existente en BD.")
    @Test
    void findDistrictNotExistsTest(){

        //arrange;
        District district = new District("ffasdf", 1100D);

        //assert
        Assertions.assertThrows(DistrictNotExistsException.class, () -> districtRepository.findPriceByLocation(district.getLocation()));
    }


    @DisplayName("Validación repositorio - Busqueda - Distrito sin nombre.")
    @Test
    void findDistrictWithoutNameTest(){

        //arrange
        District district = new District("", 1100D);

        //assert
        Assertions.assertThrows(DistrictNotExistsException.class, () -> districtRepository.findPriceByLocation(district.getLocation()));
    }


    @DisplayName("Validación repositorio - Busqueda - Distrito nombre nulo.")
    @Test
    void findDistrictWithNullNameTest(){

        //arrange
        District district = new District(null, 1100D);

        //assert
        Assertions.assertThrows(DistrictNotExistsException.class, () -> districtRepository.findPriceByLocation(district.getLocation()));
    }



}
