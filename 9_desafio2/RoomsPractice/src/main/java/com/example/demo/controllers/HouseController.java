package com.example.demo.controllers;

import com.example.demo.dtos.request.HouseRequestDto;
import com.example.demo.dtos.response.BiggestRoomResponseDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;
import com.example.demo.dtos.response.HouseValueResponseDto;
import com.example.demo.dtos.response.RoomSizeResponseDto;
import com.example.demo.exceptions.DistrictNotFound;
import com.example.demo.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @PostMapping("/size")
    public ResponseEntity getHouseSize(@Valid @RequestBody HouseRequestDto houseRequestDto) throws DistrictNotFound {
        HouseSizeResponseDto response = houseService.getHouseSize(houseRequestDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/value")
    public ResponseEntity getHouseValue(@Valid @RequestBody HouseRequestDto houseRequestDto) throws DistrictNotFound {
        HouseValueResponseDto response = houseService.getHouseValue(houseRequestDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/biggest-room")
    public ResponseEntity getBiggestRoom(@Valid @RequestBody HouseRequestDto houseRequestDto) throws DistrictNotFound {
        BiggestRoomResponseDto response = houseService.biggestRoom(houseRequestDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/rooms-size")
    public ResponseEntity getSizeByRoom(@Valid @RequestBody HouseRequestDto houseRequestDto) throws DistrictNotFound {
        RoomSizeResponseDto response = houseService.roomsArea(houseRequestDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
