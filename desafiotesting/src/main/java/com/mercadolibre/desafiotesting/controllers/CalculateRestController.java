package com.mercadolibre.desafiotesting.controllers;

import com.mercadolibre.desafiotesting.dto.HouseDto;
import com.mercadolibre.desafiotesting.dto.HouseResponseDto;
import com.mercadolibre.desafiotesting.dto.RequestHouseDto;
import com.mercadolibre.desafiotesting.exceptions.HouseException;
import com.mercadolibre.desafiotesting.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

    @Autowired
    HouseService houseService;

    @PostMapping("/calculate")
    public ResponseEntity<HouseResponseDto> calculate(@RequestBody @Valid RequestHouseDto house) throws HouseException {
        return new ResponseEntity<>(houseService.calculate(house), HttpStatus.OK);
    }
}
