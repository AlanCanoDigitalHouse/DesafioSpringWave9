package com.example.tucasitatasaciones.utils;

import com.example.tucasitatasaciones.dtos.DistrictDto;
import com.example.tucasitatasaciones.dtos.RoomDto;
import com.example.tucasitatasaciones.dtos.requests.HomeRequestDto;

import java.util.ArrayList;
import java.util.List;

public class TestUtilsGenerator {

    public static HomeRequestDto getHomeWith3Rooms(String name){
        RoomDto r1 = new RoomDto("Room 1",10.0,5.0);
        RoomDto r2 = new RoomDto("Room 2",5.0,5.0);
        RoomDto r3 = new RoomDto("Room 3",8.0,3.0);

        List<RoomDto> rooms = new ArrayList<>();
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);

        DistrictDto districtDto = new DistrictDto("Palermo", 1000.0);

        HomeRequestDto home = new HomeRequestDto();
        home.setName(name);
        home.setRooms(rooms);
        home.setDistrict(districtDto);

        return home;
    }
}
