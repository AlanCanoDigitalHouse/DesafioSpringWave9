package com.mercadolibre.calculadorametroscuadrados.utils;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class DataGeneratorTest {

    //TODO: Return HouseDTO with correct data.
    public static HouseDTO getCorrectDataHouse(String name){
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setProp_name(name);
        houseDTO.setDistrict_name("Palermo");
        houseDTO.setDistrict_price(1000.0);
        houseDTO.setRooms(getListRoomsDto());
        return houseDTO;
    }

    //TODO: Return HouseDTO without prop_name.
    public static HouseDTO getBadRequestHouse(){
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setDistrict_name("Barrio test");
        houseDTO.setDistrict_price(1000.0);
        houseDTO.setRooms(getListRoomsDto());
        return houseDTO;
    }

    public static HouseDTO getHouseWithDistrictError(){
        HouseDTO houseDTO = new HouseDTO();
        List<RoomDTO> rooms = getListRoomsDto();
        houseDTO.setDistrict_name("Barrio test");
        houseDTO.setDistrict_price(1000.0);
        houseDTO.setRooms(rooms);
        return houseDTO;
    }

    //TODO: Return request to throw HttpMessageNotReadableException
    public static String getHouseHttpMessageNotReadableException() {
        String request = "{ \"prop_name\": \"Casa test\", \"district_name\": \"Palermo\", \"district_price\": \"asdf\", " +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        return request;
    }

    //TODO: Return request to throw DistrictNotFoundException
    public static String getHouseDistrictNotFoundException(){
        String request = "{ \"prop_name\": \"Casa test\", \"district_name\": \"PalermoNotFound\", \"district_price\": 1000, " +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        return request;
    };


    private static List<RoomDTO> getListRoomsDto(){
        List<RoomDTO> rooms = new ArrayList<>();
        rooms.add(new RoomDTO("Cocina", 5.0,5.0));
        rooms.add(new RoomDTO("Dormitorio", 6.0,3.0));
        rooms.add(new RoomDTO("Garage", 7.0,4.0));
        return rooms;
    }

    public static RoomDTO getBiggestRoomDto(){
        return new RoomDTO("Garage", 7.0,4.0);
    }
}
