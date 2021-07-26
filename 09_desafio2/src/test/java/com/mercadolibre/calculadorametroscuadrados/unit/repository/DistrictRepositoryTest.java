package com.mercadolibre.calculadorametroscuadrados.unit.repository;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.Found.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.IDistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;

public class DistrictRepositoryTest {

    IDistrictRepository districtRepository;

    @BeforeEach
    @AfterEach
    private void setUp() {
        //TestUtilsGenerator.emptyDistrictsFile();
        this.districtRepository = new DistrictRepository();
    }

    @Test
    @DisplayName("Create District Non Existent")
    public void createNonExistentDistrict() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictWithPrice("Carabelas", 500.0);

        // act
        districtRepository.save(district);

        // assert
        Assertions.assertTrue(districtRepository.exists(district));
        Assertions.assertEquals("Carabelas", district.getName());
        Assertions.assertEquals(districtRepository.findByName(district.getName()), district);
    }

    @Test
    @DisplayName("Create District Existent")
    public void createExistentDistrict() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictWithPrice("Carabelas", 800.0);

        // act
        districtRepository.save(district);

        // assert
        Assertions.assertTrue(districtRepository.exists(district));
        Assertions.assertEquals(districtRepository.findByName(district.getName()), district);
    }

    @Test
    @DisplayName("Modify District Non Existent")
    public void modifyNonExistentDistrict() {
        // arrange
        DistrictDTO districtFirst = TestUtilsGenerator.getDistrictWithName("Chapinero");

        DistrictDTO districtSecond = TestUtilsGenerator.getDistrictWithName("Chapinero");
        districtSecond.setName("Chapinero Alto");
        districtSecond.setPrice(1200D);

        districtRepository.save(districtFirst);

        // act
        districtRepository.save(districtSecond);

        // assert
        Assertions.assertTrue(districtRepository.exists(districtFirst));
        Assertions.assertEquals(districtRepository.findByName(districtFirst.getName()), districtFirst);

        Assertions.assertTrue(districtRepository.exists(districtSecond));
        Assertions.assertEquals(districtRepository.findByName(districtSecond.getName()), districtSecond);

    }

    @Test
    @DisplayName("Modify Existent District")
    public void modifyExistentDistrict() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictWithPrice("Bogota", 20000D);
        districtRepository.save(district);

        // act
        district.setPrice(200D);
        districtRepository.save(district);

        // assert
        Assertions.assertTrue(districtRepository.exists(district));
        Assertions.assertEquals(districtRepository.findByName(district.getName()), district);
    }

    @Test
    @DisplayName("Find Existent District")
    public void findExistentDistrict() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictWithName("Cali");
        districtRepository.save(district);

        // act
        DistrictDTO found = districtRepository.findByName(district.getName());

        // assert
        Assertions.assertEquals(found, district);
    }

    @Test
    @DisplayName("Find Non Existent District")
    public void findNonExistentDistrict() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictWithName("Medellin");

        // act & assert
        Assertions.assertThrows(DistrictNotFoundException.class, () ->
                districtRepository.findByName(district.getName())
        );
    }

    @Test
    @DisplayName("Delete Existent District")
    public void deleteExistentDistrict() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictWithName("Madrid");
        districtRepository.save(district);

        // act
        districtRepository.delete(district.getName());

        // assert
        Assertions.assertFalse(districtRepository.exists(district));
        Assertions.assertThrows(DistrictNotFoundException.class, () ->
                districtRepository.findByName(district.getName())
        );
    }

    @Test
    @DisplayName("Delete Non Existent District")
    public void deleteNonExistentDistrict() {
        // arrange
        DistrictDTO district = TestUtilsGenerator.getDistrictWithName("Madrid");

        // act
        districtRepository.delete(district.getName());

        // assert
        Assertions.assertFalse(districtRepository.exists(district));
        Assertions.assertThrows(DistrictNotFoundException.class, () ->
                districtRepository.findByName(district.getName())
        );
    }

}
