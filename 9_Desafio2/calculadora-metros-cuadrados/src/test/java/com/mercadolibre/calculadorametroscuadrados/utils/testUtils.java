package com.mercadolibre.calculadorametroscuadrados.utils;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class testUtils{
    public static HouseDTO generate1RoomHouse(){
        HouseDTO houseDTO = new HouseDTO();
        List<RoomDTO> rooms = new ArrayList<>();
        PriceDTO price = new PriceDTO("Palermo", 2000);
        houseDTO.setName("alejo");
        houseDTO.setAddress(price);
        rooms.add(new RoomDTO("pieza", 5, 5));
        houseDTO.setRooms(rooms);
        return houseDTO;
    }

    public static Integer generate1RoomHouseSquareMeters(){
        return 5*5;
    }

    public static HouseDTO generate3RoomsHouse(){
        HouseDTO houseDTO = new HouseDTO();
        List<RoomDTO> rooms = new ArrayList<>();
        PriceDTO price = new PriceDTO("Palermo", 2000);
        houseDTO.setName("alejo");
        houseDTO.setAddress(price);
        rooms.add(new RoomDTO("pieza", 5, 5));
        rooms.add(new RoomDTO("pieza2", 10,10));
        rooms.add(new RoomDTO("living", 15,15));
        houseDTO.setRooms(rooms);
        return houseDTO;
    }

    public static Integer generate3RoomsHouseSquareMeters(){
        return 5*5+10*10+15*15;
    }

    public static HouseDTO generateNoRoomsHouse(){
        HouseDTO houseDTO = new HouseDTO();
        List<RoomDTO> rooms = new ArrayList<>();
        PriceDTO price = new PriceDTO("Palermo", 2000);
        houseDTO.setName("alejo");
        houseDTO.setAddress(price);
        houseDTO.setRooms(rooms);
        return houseDTO;
    }

    public static HouseDTO generateNullRoomsHouse(){
        HouseDTO houseDTO = new HouseDTO();
        PriceDTO price = new PriceDTO("Palermo", 2000);
        houseDTO.setName("alejo");
        houseDTO.setAddress(price);
        houseDTO.setRooms(null);
        return houseDTO;
    }

    public static HouseDTO generateNullHouse(){
        return null;
    }
}
