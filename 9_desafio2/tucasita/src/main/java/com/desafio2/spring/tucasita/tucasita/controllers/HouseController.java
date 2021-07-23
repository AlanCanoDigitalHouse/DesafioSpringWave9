package com.desafio2.spring.tucasita.tucasita.controllers;


import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseBigRoomDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseRoomsDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseSizeDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseValueDTO;
import com.desafio2.spring.tucasita.tucasita.exceptions.ServiceExceptions;
import com.desafio2.spring.tucasita.tucasita.services.IHouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/house")
public class HouseController {

    IHouseService service;

    public HouseController(IHouseService service){
        this.service = service;
    }

    @PostMapping("/size")
    public ResponseEntity<HouseSizeDTO> getHouseSize(@Valid @RequestBody HouseDTO house) throws ServiceExceptions {
        HouseSizeDTO houseSizeDto = service.getHouseSize(house);
        return new ResponseEntity<>(houseSizeDto, HttpStatus.OK);
    }

    @PostMapping("/value")
    public ResponseEntity<HouseValueDTO> getHouseValue(@Valid @RequestBody HouseDTO house) throws ServiceExceptions {
        HouseValueDTO houseValueDto = service.getHouseValue(house);
        return new ResponseEntity<>(houseValueDto, HttpStatus.OK);
    }

    @PostMapping("/bigRoom")
    public ResponseEntity<HouseBigRoomDTO> getBiggestRoom(@Valid @RequestBody HouseDTO house) throws ServiceExceptions {
        HouseBigRoomDTO houseBigRoomDto = service.getHouseBigRoom(house);
        return new ResponseEntity<>(houseBigRoomDto, HttpStatus.OK);
    }

    @PostMapping("/getRooms")
    public ResponseEntity<HouseRoomsDTO> getRoomsMts(@Valid @RequestBody HouseDTO house) throws ServiceExceptions {
        HouseRoomsDTO houseRoomsDto = service.getHouseRooms(house);
        return new ResponseEntity<>(houseRoomsDto, HttpStatus.OK);
    }
}
