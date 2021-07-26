package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculator")
public class CalculateRestController {

    @Autowired
    CalculateService calculateService;

    @PostMapping("/house")
    public HouseResponseDTO calculate(@Valid @RequestBody HouseRequestDTO house) {
        return calculateService.calculateHouse(house);
    }
}
