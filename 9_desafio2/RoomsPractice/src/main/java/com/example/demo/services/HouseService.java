package com.example.demo.services;

import com.example.demo.dtos.generals.PriceDto;
import com.example.demo.dtos.request.HouseRequestDto;
import com.example.demo.dtos.response.BiggestRoomResponseDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;
import com.example.demo.dtos.response.HouseValueResponseDto;
import com.example.demo.dtos.response.RoomSizeResponseDto;
import com.example.demo.exceptions.DistrictNotFound;
import com.example.demo.repositories.HouseRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HouseService {

    HouseRepositoryImpl houseRepository;

    public HouseService(HouseRepositoryImpl houseRepository) {
        this.houseRepository = houseRepository;
    }

    public HouseSizeResponseDto getHouseSize(HouseRequestDto houseRequestDto) throws DistrictNotFound {
        /*TODO: Validate if district_name exist in our database*/
        PriceDto district = houseRepository.searchDistrict(houseRequestDto.getDistrict_name());
        HouseSizeResponseDto response = new HouseSizeResponseDto();
        if(Objects.nonNull(district)) {
            /*TODO: Get House's size from repository*/
            response.setName(houseRequestDto.getProp_name());
            response.setArea(houseRepository.getHouseSize(houseRequestDto.getRooms()));
            return response;
        }
        throw new DistrictNotFound();
    }

    public HouseValueResponseDto getHouseValue(HouseRequestDto house) throws DistrictNotFound {
        /*TODO: Validate if district_name exist in our database*/
        PriceDto district = houseRepository.searchDistrict(house.getDistrict_name());
        HouseValueResponseDto response = new HouseValueResponseDto();
        if(Objects.nonNull(district)) {
            /*TODO: Get House's value from repository*/
            response.setName(house.getProp_name());
            Double houseSize = houseRepository.getHouseSize(house.getRooms());
            Double houseValue =  houseSize * house.getDistrict_price();
            response.setValue(houseValue);
            return response;
        }
        throw new DistrictNotFound();
    }

    public BiggestRoomResponseDto biggestRoom(HouseRequestDto house) throws DistrictNotFound {
        /*TODO: Validate if district_name exist in our database*/
        PriceDto district = houseRepository.searchDistrict(house.getDistrict_name());
        BiggestRoomResponseDto response = new BiggestRoomResponseDto();
        if(Objects.nonNull(district)) {
            /*TODO: Get biggest room from repository*/
            response.setRoom(houseRepository.biggestRoom(house.getRooms()));
            return response;
        }
        throw new DistrictNotFound();
    }

    public RoomSizeResponseDto roomsArea(HouseRequestDto house) throws DistrictNotFound {
        /*TODO: Validate if district_name exist in our database*/
        PriceDto district = houseRepository.searchDistrict(house.getDistrict_name());
        RoomSizeResponseDto response = new RoomSizeResponseDto();
        if(Objects.nonNull(district)) {
            /*TODO: Get area by rooms*/
            response.setRoomList(houseRepository.roomsArea(house.getRooms()));
            return response;
        }
        throw new DistrictNotFound();
    }

}
