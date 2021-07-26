package com.example.tucasitatasaciones.units.repositories;

import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.repositories.DistrictRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.tucasitatasaciones.units.utils.UnitTestBuilder.*;

public class PropertyRepositoryTests {

    DistrictRepositoryImpl propertyRepository = new DistrictRepositoryImpl();

    @Test
    void districtExists() {
        // Arrange
        PropertyDTO property = create1Property2Enviroments();
        // Act
        Boolean exists = propertyRepository.checkIfDistrictExistsFor(property);
        // Assert
        Assertions.assertTrue(exists);
    }

    @Test
    void districtDoesntExists() {
        // Arrange
        PropertyDTO property = create1Property2Enviroments();
        property.getDistrict().setDistrict_name("Not found");
        // Act
        Boolean notExists = propertyRepository.checkIfDistrictExistsFor(property);
        // Arrange
        Assertions.assertFalse(notExists);
    }
}
