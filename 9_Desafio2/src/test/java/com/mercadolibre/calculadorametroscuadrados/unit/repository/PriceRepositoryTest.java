package com.mercadolibre.calculadorametroscuadrados.unit.repository;


import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceRepositoryTest {

    PriceRepository priceRepository;

    public PriceRepositoryTest() {
        this.priceRepository = new PriceRepositoryImpl();
    }

    @Test
    void findPriceDtoByLocation() throws NotFoundLocation {
        //Arrange
        String expected = "Palermo";
        //Act
        PriceDTO priceDTO = priceRepository.findLocation(expected);

        //assert
        assertEquals(expected, priceDTO.getLocation());
    }

    @Test
    void findPriceDtoByLocationAndThrowException() throws NotFoundLocation {
        //Arrange
        String expected = "San Antonio de Areco";
        //Act && assert
        assertThrows(NotFoundLocation.class, ()-> priceRepository.findLocation(expected));
    }

    @Test
    void findPriceDtoByNullLocationAndThrowException() throws NotFoundLocation {
        //Act && assert
        assertThrows(NotFoundLocation.class, ()-> priceRepository.findLocation(null));
    }

}
