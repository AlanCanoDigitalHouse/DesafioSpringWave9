package com.tucasitatasaciones.unit.repositories;

import com.tucasitatasaciones.DTOs.PriceDTO;
import com.tucasitatasaciones.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/* Leer la documentación README.md para verificar que se existe el archivo de prueba en resources/static.price.json */
@SpringBootTest
public class PriceRepositoryTest {

    private final PriceRepositoryImpl priceRepository = new PriceRepositoryImpl();

    @Test
    @DisplayName("Método: findPriceLocation(Nombre del Barrio) - Caso 1: Barrio existente")
    public void testFindPriceLocationCase1() {
        // arrange
        PriceDTO district1 = new PriceDTO("Palermo", 1000.0);
        // act
        PriceDTO district2 = priceRepository.findPriceLocation("Palermo");
        // assert
        assertEquals(district1, district2);
    }

    @Test
    @DisplayName("Método: findPriceLocation(Nombre del Barrio) - Caso 2: Barrio no existente")
    public void testFindPriceLocationCase2() {
        PriceDTO district = priceRepository.findPriceLocation("#####");
        assertNull(district);
    }

    @Test
    @DisplayName("Método: findPriceLocation(Nombre del Barrio) - Caso 3: Barrio existente - Difiere el precio")
    public void testFindPriceLocationCase3() {
        // arrange
        PriceDTO district1 = new PriceDTO("Palermo", 99999);
        // act
        PriceDTO district2 = priceRepository.findPriceLocation("Palermo");
        // assert
        assertNotEquals(district1.getDistrict_price(), district2.getDistrict_price());
    }
}
