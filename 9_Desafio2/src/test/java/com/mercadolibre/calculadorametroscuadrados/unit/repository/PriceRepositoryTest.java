package com.mercadolibre.calculadorametroscuadrados.unit.repository;


import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repository - Unit Test")
public class PriceRepositoryTest {

    PriceRepository priceRepository;

    public PriceRepositoryTest() {
        this.priceRepository = new PriceRepositoryImpl();
    }

    @Test
    @DisplayName("Test: Buscar un districto existente")
    void findPriceDtoByLocation() throws NotFoundLocation {
        //Arrange
        String expected = "Palermo";
        //Act
        PriceDTO priceDTO = priceRepository.findLocation(expected);

        //assert
        assertEquals(expected, priceDTO.getLocation());
    }

    @Test
    @DisplayName("Test: Buscar un districto inexistente y arrojar la exception correspondiente")
    void findPriceDtoByLocationAndThrowException()  {
        //Arrange
        String expected = "San Antonio de Areco";
        //Act && assert
        assertThrows(NotFoundLocation.class, ()-> priceRepository.findLocation(expected));
    }

    @Test
    @DisplayName("Test: Buscar un districto nulo y arrojar la exception correspondiente")
    void findPriceDtoByNullLocationAndThrowException()  {
        //Act && assert
        assertThrows(NotFoundLocation.class, ()-> priceRepository.findLocation(null));
    }

}
