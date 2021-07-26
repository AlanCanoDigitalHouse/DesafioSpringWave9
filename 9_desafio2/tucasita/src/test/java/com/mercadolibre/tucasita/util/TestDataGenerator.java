package com.mercadolibre.tucasita.util;

import com.mercadolibre.tucasita.domain.District;
import com.mercadolibre.tucasita.domain.House;
import com.mercadolibre.tucasita.domain.Room;
import com.mercadolibre.tucasita.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    public static House getValidHouse() {
        District district = new District("Puerto Madero", 2000);
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Kitchen", 15,10));
        rooms.add(new Room("Bedroom", 10,7));
        rooms.add(new Room("Bathroom", 5,5));
        rooms.add(new Room("Study", 14,12));

        return new House("MiCasita", district, rooms);
    }

    public static District getDistrictOfValidHouse() {
        return new District("Puerto Madero", 2000);
    }

    public static RoomDTO getBiggestRoomOfValidHouse() {
        return new RoomDTO("Study", 168);
    }

    public static List<RoomDTO> getRoomSizeList() {

        List<RoomDTO> result = new ArrayList<>();

        result.add(new RoomDTO("Kitchen", 15*10));
        result.add(new RoomDTO("Bedroom", 10*7));
        result.add(new RoomDTO("Bathroom", 5*5));
        result.add(new RoomDTO("Study", 14*12));

        return result;
    }

    public static House getInvalidHouseByDistrictName() {
        District district = new District("Puerto", 2000);
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Kitchen", 15,10));
        rooms.add(new Room("Bedroom", 10,7));
        rooms.add(new Room("Bathroom", 5,5));
        rooms.add(new Room("Study", 14,12));

        return new House("MiCasa", district, rooms);
    }

    public static House getInvalidHouseByDistrictPrice() {
        District district = new District("Puerto Madero", 1);
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Kitchen", 15,10));
        rooms.add(new Room("Bedroom", 10,7));
        rooms.add(new Room("Bathroom", 5,5));
        rooms.add(new Room("Study", 14,12));

        return new House("MiCasa", district, rooms);
    }

}
