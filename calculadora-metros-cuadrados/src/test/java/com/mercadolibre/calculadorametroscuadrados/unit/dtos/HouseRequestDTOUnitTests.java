package com.mercadolibre.calculadorametroscuadrados.unit.dtos;


import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseRequestDTOUnitTests {

    @Test
    void calculateRoomAreaCorrectly() {
        //Arrange
        RoomDTO room = new RoomDTO("Sala común", 5.0, 9.0);
        //Act
        Double area = room.getArea();
        //Assert
        assertEquals(area, 5 * 9);
    }

    @Test
    void calculationOfHouseAreaIsCorrect() {
        //Arrange
        HouseRequestDTO house = HouseRequestInitializer.house();
        List<RoomDTO> rooms = house.getRooms();

        //Act
        Double area = 0D;
        for (RoomDTO room : rooms)
            area += room.getArea();

        //Assert
        assertEquals(house.calculateHouseArea(), area);
    }

    @Test
    void gettingBiggestRoomInHouse() {
        //Arrange
        List<RoomDTO> rooms = HouseRequestInitializer.house().getRooms();
        RoomDTO maxRoom = HouseRequestInitializer.biggestRoomInHouse();

        //Act
        RoomDTO biggestRoomFoundArea = rooms
                .stream()
                .max(Comparator.comparing(RoomDTO::getArea))
                .get();

        //Assert
        assertDoesNotThrow(biggestRoomFoundArea::getArea);
        assertEquals(maxRoom.getArea(), biggestRoomFoundArea.getArea());
    }


}
