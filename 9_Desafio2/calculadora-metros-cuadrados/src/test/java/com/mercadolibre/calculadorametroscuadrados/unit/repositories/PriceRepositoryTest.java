package com.mercadolibre.calculadorametroscuadrados.unit.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DuplicatedLocationException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.Location;

public class PriceRepositoryTest {

    PriceRepositoryImpl priceRepository;

    @BeforeEach
    void setUp() {
        this.priceRepository = new PriceRepositoryImpl();
    }

    @Test
    void insertNonExistingPriceAndFind() {
        //arrange
        PriceDTO price = new PriceDTO("Santiago", 2500);

        //act
        priceRepository.insertPrice(price);
        PriceDTO newPrice = priceRepository.findPriceLocation(price.getLocation());

        //assert
        Assertions.assertEquals(price, newPrice);
    }

    @Test
    void insertExistingPriceAndFind()  {
        //arrange
        PriceDTO price = new PriceDTO("Valparaiso", 2000);

        //act
        priceRepository.insertPrice(price);

        //assert
        Assertions.assertThrows(DuplicatedLocationException.class,() -> priceRepository.insertPrice(price));
    }

    @Test
    void findPriceByExistingLocation() throws LocationNotFoundException {
        //arrange
        String location = "Palermo";
        PriceDTO mockPrice = new PriceDTO(location, 1000);

        //act
        PriceDTO priceInRepository = priceRepository.findPriceLocation(location);

        //assert
        Assertions.assertEquals(mockPrice, priceInRepository);
    }

    @Test
    void findPriceByNullLocation() {
        //assert
        Assertions.assertThrows(
                LocationNotFoundException.class,
                () ->  priceRepository.findPriceLocation(null)
        );
    }

    @Test
    void findPriceByEmptyLocation() {
        //arrange
        String location = "";

        //assert
        Assertions.assertThrows(
                LocationNotFoundException.class,
                () ->  priceRepository.findPriceLocation(location)
        );
    }

    @Test
    void findPriceByNonExistentLocation(){
        //arrange
        String location = "hehe";

        //assert
        Assertions.assertThrows(
                LocationNotFoundException.class,
                () ->  priceRepository.findPriceLocation(location)
        );
    }

    @Test
    void findPriceAndCompareWithDifferentPrice() throws LocationNotFoundException {
        //arrange
        String location = "Palermo";
        PriceDTO mockPrice = new PriceDTO(location, 3000);

        //act
        PriceDTO priceInRepository = priceRepository.findPriceLocation(location);

        //assert
        Assertions.assertNotEquals(mockPrice, priceInRepository);
    }

    @Test
    void findTwoPricesAndCompareThem() throws LocationNotFoundException {
        //arrange
        String location1 = "Palermo";
        String location2 = "Belgrano";

        //act
        PriceDTO priceInRepository1 = priceRepository.findPriceLocation(location1);
        PriceDTO priceInRepository2 = priceRepository.findPriceLocation(location2);

        //assert
        Assertions.assertFalse(priceInRepository1.equals(priceInRepository2));
    }

    @Test
    void findTheSamePriceTwoTimesAndCompare(){
        //arrange
        String location = "Palermo";

        //act
        PriceDTO priceInRepository1 = priceRepository.findPriceLocation(location);
        PriceDTO priceInRepository2 = priceRepository.findPriceLocation(location);

        //assert
        Assertions.assertEquals(priceInRepository1, priceInRepository2);
    }



}
