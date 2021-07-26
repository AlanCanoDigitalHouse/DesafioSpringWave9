package com.example.casitas.controllers;


import com.example.casitas.dtos.*;
import com.example.casitas.exceptions.DistrictNotFoundException;
import com.example.casitas.services.HouseService;
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
public class TuCasitaController {

    private final HouseService houseService;

    public TuCasitaController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/squareMeters")
    public ResponseEntity<HouseResponseDTO> getSquareMeters(@Valid @RequestBody HouseDTO house) {
        return new ResponseEntity<>(houseService.getSquareMeters(house), HttpStatus.OK);
    }

    @PostMapping("/price")
    public ResponseEntity<HouseDetailResponseDTO> getPrice(@Valid @RequestBody HouseDTO house) throws DistrictNotFoundException {
        return new ResponseEntity<>(houseService.getPrice(house), HttpStatus.OK);
    }

    @PostMapping("/biggerEnv")
    public ResponseEntity<EnvironmentDTO> getBiggerEnvironment(@Valid @RequestBody HouseDTO house) {
        return new ResponseEntity<>(houseService.getBiggerEnvironment(house), HttpStatus.OK);
    }

    @PostMapping("/squareMetersEnvironment")
    public ResponseEntity<List<EnvironmentResponseDTO>> getSquareMetersEnvironments(@Valid @RequestBody HouseDTO house) {
        return new ResponseEntity<>(houseService.getListEnvironments(house), HttpStatus.OK);
    }
}
