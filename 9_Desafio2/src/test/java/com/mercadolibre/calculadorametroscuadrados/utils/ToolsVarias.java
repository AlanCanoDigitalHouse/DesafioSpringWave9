package com.mercadolibre.calculadorametroscuadrados.utils;

import com.mercadolibre.calculadorametroscuadrados.dto.*;

import java.util.ArrayList;
import java.util.List;

public class ToolsVarias {

    public static List<RoomDTO> loadTwoRooms(){
        RoomDTO dto = new RoomDTO();
        dto.setEnvironment_length(10D);
        dto.setEnvironment_width(5D);
        dto.setEnvironment_name("Living");
        RoomDTO dto1 = new RoomDTO();
        dto1.setEnvironment_length(20D);
        dto1.setEnvironment_width(10D);
        dto1.setEnvironment_name("Comedor");
        List<RoomDTO> rooms = new ArrayList<>();
        rooms.add(dto);
        rooms.add(dto1);
        return rooms;
    }

    public static List<RoomDTO> loadOneRoom(){
        RoomDTO dto = new RoomDTO();
        dto.setEnvironment_length(10D);
        dto.setEnvironment_width(5D);
        dto.setEnvironment_name("Comedor");
        List<RoomDTO> rooms = new ArrayList<>();
        rooms.add(dto);
        return rooms;
    }

    public static HouseDTO returnSingleHouseDTO(){
        HouseDTO dto = new HouseDTO();
        dto.setProp_name("Casita");
        dto.setRooms(loadTwoRooms());
        return dto;
    }

    public static HouseTotalSizeRequestDTO returnHouseDTO(){
        HouseTotalSizeRequestDTO dto = new HouseTotalSizeRequestDTO();
        dto.setProp_name("Casita");
        dto.setDistrict_name("Centro");
        dto.setRooms(loadOneRoom());
        return dto;
    }

    public static HousePriceRequestDTO returnHousePriceDTO(){
        HousePriceRequestDTO dto = new HousePriceRequestDTO();
        dto.setProp_name("Casita");
        dto.setDistrict_name("Centro");
        dto.setDistrict_price(1000D);
        dto.setRooms(loadOneRoom());
        return dto;
    }
    public static HouseSizesResponseDTO returnAllDTO(){
        HouseSizesResponseDTO dto = new HouseSizesResponseDTO();
        dto.setProp_name("Casita");
        RoomCountResponseDTO r1 = new RoomCountResponseDTO();
        RoomCountResponseDTO r2 = new RoomCountResponseDTO();
        r1.setEnvironment_name("Living");
        r1.setSize(50D);
        r2.setEnvironment_name("Comedor");
        r2.setSize(200D);
        List<RoomCountResponseDTO> dtos = new ArrayList<>();
        dtos.add(r1);
        dtos.add(r2);
        dto.setEnvironment_sizes(dtos);

        return dto;
    }

}
