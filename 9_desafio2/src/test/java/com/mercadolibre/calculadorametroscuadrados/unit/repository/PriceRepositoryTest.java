package com.mercadolibre.calculadorametroscuadrados.unit.repository;

import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceRepositoryTest {

    @Test
    @DisplayName("Cargo correctamente todos los datos del Json.")
    void correctLoadingInfo(){
        //Act
        PriceRepositoryImpl priceRepository = new PriceRepositoryImpl();

        //Assert
        Assertions.assertDoesNotThrow(()->priceRepository.findPriceLocation("Palermo"));
        Assertions.assertDoesNotThrow(()->priceRepository.findPriceLocation("Belgrano"));
        Assertions.assertDoesNotThrow(()->priceRepository.findPriceLocation("Recoleta"));
        Assertions.assertDoesNotThrow(()->priceRepository.findPriceLocation("Puerto Madero"));
    }

    @Test
    @DisplayName("Exepción cuando busco una locación que no fue cargada.")
    void errorWhenSearchingInvalidLocation(){
        //Act
        PriceRepositoryImpl priceRepository = new PriceRepositoryImpl();

        //Assertion
        Assertions.assertThrows(LocationNotFound.class,()->priceRepository.findPriceLocation("Capital"));
    }
}
