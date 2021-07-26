package com.example.demo.util;

import com.example.demo.dtos.generals.PriceDto;
import com.example.demo.dtos.request.HouseRequestDto;
import com.example.demo.dtos.request.RoomRequestDto;
import com.example.demo.dtos.response.BiggestRoomResponseDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {

    public static PriceDto createValidPriceDtoTest() {
        PriceDto price = new PriceDto();
        price.setDistrict_name("Recoleta");
        price.setDistrict_price(900.0);
        return price;
    }

    public static PriceDto createInvalidPriceDtoTest() {
        PriceDto price = new PriceDto();
        price.setDistrict_name("Recolta");
        price.setDistrict_price(900.0);
        return price;
    }

    public static HouseSizeResponseDto createValidHouseSizeResponse() {
        HouseSizeResponseDto houseSize = new HouseSizeResponseDto();
        houseSize.setName("Miriam");
        houseSize.setArea(84.0);
        return houseSize;
    }
    
    public static List<RoomRequestDto> createListValidRooms() {
        RoomRequestDto room1 = new RoomRequestDto("Miriam", 3.0, 4.0);
        RoomRequestDto room2 = new RoomRequestDto("Omar", 4.0, 6.0);
        RoomRequestDto room3 = new RoomRequestDto("Olivia", 12.0, 4.0);
        List<RoomRequestDto> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        return rooms;
    }

    public static RoomRequestDto createValidBiggestRoom() {
        RoomRequestDto room = new RoomRequestDto("Olivia", 12.0, 4.0);
        return room;
    }

    public static HouseRequestDto createHouseWithThreeRooms() {
        HouseRequestDto house = new HouseRequestDto();
        List<RoomRequestDto> rooms = createListValidRooms();
        house.setDistrict_name("Belgrano");
        house.setDistrict_price(900.0);
        house.setProp_name("Miriam");
        house.setRooms(rooms);
        return house;
    }

    public static HouseRequestDto createInvalidHouseWithThreeRooms() {
        HouseRequestDto house = new HouseRequestDto();
        List<RoomRequestDto> rooms = createListValidRooms();
        house.setDistrict_name("Belgranojduhddh");
        house.setDistrict_price(900.0);
        house.setProp_name("Miriam");
        house.setRooms(rooms);
        return house;
    }
 }
