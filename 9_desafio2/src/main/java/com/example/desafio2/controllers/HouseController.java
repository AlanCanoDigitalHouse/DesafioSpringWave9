package com.example.desafio2.controllers;

import com.example.desafio2.dtos.*;
import com.example.desafio2.services.HouseService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/getSquareMeters")
    public ResponseEntity<HouseResponseDTO> getSquareMeters(@Valid @RequestBody HouseDTO house){
        return new ResponseEntity<>(houseService.getSquareMeters(house), HttpStatus.OK);
    }

    @PostMapping("/getPrice")
    public ResponseEntity<HouseDetailResponseDto> getValueByEnvAndDistrict(@Valid @RequestBody HouseDTO house){
        return new ResponseEntity<>(houseService.getPrice(house),HttpStatus.OK);
    }

    @PostMapping("/getBiggerEnv")
    public ResponseEntity<EnvDTO> getBiggerEnv(@Valid @RequestBody HouseDTO house){
        return new ResponseEntity<>(houseService.getBiggerEnv(house),HttpStatus.OK);
    }

    @PostMapping("/getSquareMetersEnv")
    public ResponseEntity<List<EnvResponseDto>> getSquareMetersEnv(@Valid @RequestBody HouseDTO house){
        return new ResponseEntity<>(houseService.getListEnv(house),HttpStatus.OK);
    }
}

