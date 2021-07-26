package com.mercadolibre.tucasitatasaciones.unit.repositories;

import com.mercadolibre.tucasitatasaciones.entities.District;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.IDistrictRepository;
import com.mercadolibre.tucasitatasaciones.repositories.implementations.DistrictRepositoryImpl;
import com.mercadolibre.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DistrictRepositoryTest {

    IDistrictRepository iDistrictRepository;

    @BeforeEach
    void init(){
        iDistrictRepository = new DistrictRepositoryImpl();
        TestUtilsGenerator.initDataBase();
        TestUtilsGenerator.appendNewDistrict(new District("Palermo", 1000D));
    }

    @Test
    @DisplayName("District exist")
    void districtExistTest() throws DistrictNotFoundException {
        District districtExpected = new District("Palermo",1000D);

        District realDistrict = iDistrictRepository.findByName("Palermo");

        Assertions.assertEquals(districtExpected, realDistrict);
    }

    @Test
    @DisplayName("District NOT exist")
    void districtNotExistTest() {
        Assertions.assertThrows(DistrictNotFoundException.class, () -> iDistrictRepository.findByName("Don Torcuato"));
    }

}
