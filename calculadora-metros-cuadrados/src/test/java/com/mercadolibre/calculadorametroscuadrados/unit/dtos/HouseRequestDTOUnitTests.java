package com.mercadolibre.calculadorametroscuadrados.unit.dtos;


import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class HouseRequestDTOUnitTests {

    /** (PUNTO 4) Devuelve el cálculo correcto del total de metros cuadrados de un ambiente. */
    @Test
    void calculateRoomAreaCorrectly() {
        //Arrange
        RoomDTO room = new RoomDTO("Sala común", 5.0, 9.0);
        //Act
        Double area = room.getArea();
        //Assert
        assertEquals(area, 5 * 9);
    }

    /** Devuelve el cálculo correcto del total de metros cuadrados de cada ambiente. */
    @Test
    void calculateAreaOfEachRoomSeparatelyAndCorrectly() {
        //Arrange
        HouseRequestDTO house = HouseRequestInitializer.house();
        List<RoomDTO> rooms = house.getRooms();

        //Act
        List<Double> roomAreas = rooms.stream().map( r->r.getArea()).collect(Collectors.toList());

        //Assert
        assertEquals(roomAreas.get(0), 2.0*1.5);
        assertEquals(roomAreas.get(1), 8.0*4.0);
        assertEquals(roomAreas.get(2), 3.0*5.0);
    }

    /** (PUNTO 1) Devuelve el cálculo correcto del total de metros cuadrados de una propiedad.*/
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

    /** (PUNTO 3) Verifica que efectivamente se devuelva el ambiente con mayor tamaño.*/
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

    /** (PUNTO 3) Verifica que efectivamente se devuelva el ambiente con mayor tamaño. No existe ninguno que lo supere.*/
    @Test
    void biggestRoomInHouseIsBiggerOrEqualToAllRoomsInHouse() {
        //Arrange
        List<RoomDTO> rooms = HouseRequestInitializer.house().getRooms();
        RoomDTO maxRoom = HouseRequestInitializer.biggestRoomInHouse();

        //Assert
        for (RoomDTO room : rooms)
            assertTrue(maxRoom.getArea()>=room.getArea());
    }

}
