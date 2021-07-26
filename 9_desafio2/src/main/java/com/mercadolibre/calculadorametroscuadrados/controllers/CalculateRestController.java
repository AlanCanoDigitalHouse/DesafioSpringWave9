package com.mercadolibre.calculadorametroscuadrados.controllers;

import com.mercadolibre.calculadorametroscuadrados.dtos.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectDistrictPriceException;
import com.mercadolibre.calculadorametroscuadrados.services.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

    @Autowired
    CalculateService calculateService;

    @PostMapping("/calculate")
    public HouseResponseDTO calculate(@RequestBody @Valid HouseDTO house) throws DistrictNotFoundException, IncorrectDistrictPriceException {
        return calculateService.calculate(house);
    }
}
