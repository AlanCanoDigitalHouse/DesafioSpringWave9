package com.mercado_libre.bootcamp.desafio2.controllers;

import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.mercado_libre.bootcamp.desafio2.services.calculator.HouseCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculate")
public class HouseCalculatorController {

    private final HouseCalculatorService houseCalculatorService;

    public HouseCalculatorController(HouseCalculatorService houseCalculatorService) {
        this.houseCalculatorService = houseCalculatorService;
    }

    @GetMapping(value = "/house")
    public ResponseEntity<HouseResponseDTO> calculate(@Valid @RequestBody HouseRequestDTO house) {
        return new ResponseEntity<>(houseCalculatorService.calculate(house), HttpStatus.OK);
    }
}
