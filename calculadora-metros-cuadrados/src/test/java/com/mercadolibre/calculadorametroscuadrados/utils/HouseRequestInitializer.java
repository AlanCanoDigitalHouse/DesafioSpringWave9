package com.mercadolibre.calculadorametroscuadrados.utils;

import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class HouseRequestInitializer {


    public static HouseRequestDTO house() {
        HouseRequestDTO house = new HouseRequestDTO(
                "Lo de Jorgito",
                "Palermo",
                2200.0,
                rooms()
        );
        return house;
    }

    public static RoomDTO biggestRoomInHouse() {
        return new RoomDTO("Quincho", 8.0, 4.0);
    }

    public static List<RoomDTO> rooms() {
        List<RoomDTO> rooms = new ArrayList<>();
        rooms.add(new RoomDTO("Baño", 2.0, 1.5));
        rooms.add(new RoomDTO("Quincho", 8.0, 4.0));
        rooms.add(new RoomDTO("Cuarto de huéspedes", 3.0, 5.0));
        return rooms;
    }
}
