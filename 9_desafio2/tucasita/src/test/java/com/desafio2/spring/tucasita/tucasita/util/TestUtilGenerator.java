package com.desafio2.spring.tucasita.tucasita.util;

import com.desafio2.spring.tucasita.tucasita.dtos.request.DistrictDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {

    public static HouseDTO invalidHouseName() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Invalid name",23);
        HouseDTO houseDTO = new HouseDTO("nombre con minuscula", districtDTO, rooms);
        return houseDTO;
    }

    public static HouseDTO invalidHouseLongName() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Invalid name",23);
        HouseDTO houseDTO = new HouseDTO(
                "Este es un nombre muy muy muy muy muy muy muy muy muy muy muy muy muy muy laaaargoo",
                districtDTO, rooms);
        return houseDTO;
    }

    public static HouseDTO invalidDistrictNameHouse() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Invalid name",23);
        HouseDTO houseDTO = new HouseDTO("Tucasita", districtDTO, rooms);
        return houseDTO;
    }

    public static HouseDTO invalidDistrictPriceHouse() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Palermo",4001);
        HouseDTO houseDTO = new HouseDTO("Tucasita", districtDTO, rooms);
        return houseDTO;
    }



    public static HouseDTO simpleHouseForTestSize33() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Palermo", 1000);
        return new HouseDTO("Tucasita", districtDTO, rooms);
    }

    public static HouseDTO simpleHouseForTestSizeRecoleta() {
        List<RoomDTO> rooms = createRooms();
        DistrictDTO districtDTO = new DistrictDTO("Recoleta", 900);
        return new HouseDTO("Tucasita", districtDTO, rooms);
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

    public static HouseDTO emptyRoomsList() {
        ArrayList<RoomDTO> emptyRooms = new ArrayList<>();
        DistrictDTO districtDTO = new DistrictDTO("Palermo", 1000);
        HouseDTO house = new HouseDTO("Empty rooms", districtDTO, emptyRooms);
        return house;
    }
}
