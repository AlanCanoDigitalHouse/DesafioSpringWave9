package com.example.demo.repositories;

import com.example.demo.dtos.generals.PriceDto;
import com.example.demo.dtos.request.RoomRequestDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;
import com.example.demo.exceptions.DistrictNotFound;

import java.util.List;

public interface HouseRepositoryImpl {

    PriceDto searchDistrict(String environment_name) throws DistrictNotFound;
    Double getHouseSize(List<RoomRequestDto> rooms);
    RoomRequestDto biggestRoom(List<RoomRequestDto> rooms);
    List<RoomRequestDto> roomsArea(List<RoomRequestDto> rooms);
}
