package com.mercadolibre.desafiotesting.utils;

import com.mercadolibre.desafiotesting.dto.HouseDto;
import com.mercadolibre.desafiotesting.dto.HouseRequestDto;
import com.mercadolibre.desafiotesting.dto.RoomDto;

import java.util.ArrayList;
import java.util.List;


public class HouseTestUtils {

    public static HouseRequestDto getRequestHouse(Double price) {

        List<RoomDto> roomDtoList = new ArrayList<>();
        RoomDto roomDto1 = new RoomDto("Cuarto", 10.0, 10.0);
        RoomDto roomDto2 = new RoomDto("Sala", 12.0, 10.0);
        roomDtoList.add(roomDto1);
        roomDtoList.add(roomDto2);
        HouseDto houseDto = new HouseDto("Casa", "Barrio flores", roomDtoList);
        return new HouseRequestDto(houseDto, price);
    }
}
