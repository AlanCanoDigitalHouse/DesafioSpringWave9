package com.mercadolibre.calculadorametroscuadrados.unit.repositories;

import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceRepositoryTest {
    @Test
    @DisplayName("Test validacion barrio encontrado en base de datos.")
    public void findDistrictByNameSuccessTest(){
        // Arrange
        String district = "Palermo";
        PriceRepositoryImpl priceRepository = new PriceRepositoryImpl();

        // Act
        Boolean districtExist = priceRepository.findDistrictByName(district);

        // Assert
        Assertions.assertTrue(districtExist);
    }

    @Test
    @DisplayName("Test DistrictNotFoundException: barrio no encontrado en base de datos.")
    public void findDistrictByNameDistrictNotFoundException(){
        // Arrange
        String district = "This district not found";
        PriceRepositoryImpl priceRepository = new PriceRepositoryImpl();

        // Act and Assert
        Assertions.assertThrows(DistrictNotFoundException.class , () -> priceRepository.findDistrictByName(district));
    }

    @Test
    @DisplayName("Test DistrictNotFoundException: barrio no encontrado en base de datos (null).")
    public void findDistrictByNameNullDistrictNotFoundException(){
        // Arrange
        String district = null;
        PriceRepositoryImpl priceRepository = new PriceRepositoryImpl();

        // Act and Assert
        Assertions.assertThrows(DistrictNotFoundException.class , () -> priceRepository.findDistrictByName(district));
    }
}
