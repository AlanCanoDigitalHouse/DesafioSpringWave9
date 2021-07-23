package com.mercadolibre.calculadorametroscuadrados.unit;


import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.BadRequestException;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class RepositoryTest {
    PriceRepositoryImpl repo = new PriceRepositoryImpl();

    @Test
    void verificarBarrio() {
        //arrange
        String locatonName = "Palermo";
        Integer expectedPrice = 1000;
        String expectedLocation = "Palermo";
        //act
        PriceDTO current = repo.findPriceLocation(locatonName);

        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedLocation, current.getLocation()),
                () -> Assertions.assertEquals(expectedPrice, current.getPrice())
        );
    }
    @Test
    void NotFoundIngredient() {
        //arrange
        String ingredientName = "notFound";

        //act && assert
        Assertions.assertThrows(BadRequestException.class, () -> repo.findPriceLocation(ingredientName));
    }


}
