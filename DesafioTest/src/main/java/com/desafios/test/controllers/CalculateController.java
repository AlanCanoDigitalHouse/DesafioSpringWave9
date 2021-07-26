package com.desafios.test.controllers;

import com.desafios.test.dtos.request.HouseRequestDTO;
import com.desafios.test.dtos.response.HouseResponseDTO;
import com.desafios.test.services.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) { this.calculateService = calculateService; }

    //Calculo de superficie de la propiedad
    @PostMapping("/squareFeet")
    public ResponseEntity<HouseResponseDTO> squareFeet(@Valid @RequestBody HouseRequestDTO house){
        return new ResponseEntity<>(this.calculateService.calculateHouseSquareFeet(house), HttpStatus.OK);
    }

    //Calculo de superficie del precio de la propiedad
    @PostMapping("/price")
    public ResponseEntity<HouseResponseDTO> price(@Valid @RequestBody HouseRequestDTO house){
        return new ResponseEntity<>(this.calculateService.calculatePrice(house), HttpStatus.OK);
    }

    //Calculo de la habitación más grande de la propiedad
    @PostMapping("/biggestRoom")
    public ResponseEntity<HouseResponseDTO> biggestRoom(@Valid @RequestBody HouseRequestDTO house){
        return new ResponseEntity<>(this.calculateService.calculateBiggestRoom(house), HttpStatus.OK);
    }

    //Calculo de superficie de cada habitación de la propiedad
    @PostMapping("/roomSquareFeet")
    public ResponseEntity<HouseResponseDTO> roomSquareFeet(@Valid @RequestBody HouseRequestDTO house){
        return new ResponseEntity<>(this.calculateService.calculateRoomSquareFeet(house), HttpStatus.OK);
    }
}
