package com.desafio2.spring.tucasita.tucasita.util;

import com.desafio2.spring.tucasita.tucasita.dtos.request.DistrictDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {

    public static HouseDTO invalidDistrictNameHouse() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Invalid name",23);
        HouseDTO houseDTO = new HouseDTO("Tu casita", districtDTO, rooms);
        return houseDTO;
    }

    public static HouseDTO invalidDistrictPriceHouse() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Palermo",23);
        HouseDTO houseDTO = new HouseDTO("Tu casita", districtDTO, rooms);
        return houseDTO;
    }

    public static HouseDTO simpleHouseForTestSize33() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Palermo", 1000);
        return new HouseDTO("Tu casita", districtDTO, rooms);
    }

    private static List<RoomDTO> createRooms(){
        ArrayList<RoomDTO> rooms = new ArrayList<>();

        RoomDTO room1 = new RoomDTO("Cocina", 2.3, 3.0);
        RoomDTO room2 = new RoomDTO("Living", 4.3, 3.0);
        RoomDTO room3 = new RoomDTO("Dormitorio", 3.3, 4.0);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        return rooms;
    }
}
