package com.example.tucasita.controllers;

import com.example.tucasita.DTO.request.HouseRequestDTO;
import com.example.tucasita.DTO.response.HouseResponseDTO;
import com.example.tucasita.services.interfaces.HouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class SquareMeterCalculatorRESTController {

    HouseService houseService;

    public SquareMeterCalculatorRESTController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/calculateForHouse")
    public ResponseEntity<HouseResponseDTO> calculateForHouse(@Valid @RequestBody HouseRequestDTO houseRequestDTO) {
        return new ResponseEntity<>(houseService.calculateForHouse(houseRequestDTO), HttpStatus.OK);
    }

}
