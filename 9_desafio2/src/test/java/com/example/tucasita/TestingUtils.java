package com.example.tucasita;

import com.example.tucasita.DTO.request.HouseRequestDTO;
import com.example.tucasita.DTO.request.RoomRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class TestingUtils {
    public static HouseRequestDTO getValidHouseWith3Rooms() {
        HouseRequestDTO hrDTO = new HouseRequestDTO();
        hrDTO.setName("CasaValida");
        hrDTO.setDistrictName("Sur");
        hrDTO.setDistrictPrice(300.0);
        List<RoomRequestDTO> rooms = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RoomRequestDTO room = new RoomRequestDTO();
            room.setName("Room " + i);
            room.setLength((double) (i + 1));
            room.setWidth((double) (i + 2));
            rooms.add(room);
        }
        hrDTO.setRooms(rooms);
        return hrDTO;
    }
}
