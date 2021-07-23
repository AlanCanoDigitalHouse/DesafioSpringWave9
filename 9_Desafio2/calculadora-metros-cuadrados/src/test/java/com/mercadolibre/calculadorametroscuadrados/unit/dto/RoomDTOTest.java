package com.mercadolibre.calculadorametroscuadrados.unit.dto;

import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomDTOTest {
    @Test
    void calculateSquareMeters() {
        //arrange
        Integer width = 20;
        Integer length = 20;
        RoomDTO room = new RoomDTO("test", 20, 20);
        Integer expected = width*length;

        //act
        Integer result = room.getSquareFeet();

        //assert
        Assertions.assertEquals(expected, result);
    }
}
