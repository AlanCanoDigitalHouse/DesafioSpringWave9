package com.meli.joescaos.desafiotesting.controllers;

import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.HouseResponseDTO;
import com.meli.joescaos.desafiotesting.services.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

    private CalculateService calculateService;

    public CalculateRestController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<HouseResponseDTO> calculate(@Valid @RequestBody HouseDTO house){
        return new ResponseEntity(calculateService.calculate(house), HttpStatus.OK);
    }

}
