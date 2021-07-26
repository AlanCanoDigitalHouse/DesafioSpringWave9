package com.mercadolibre.tucasitaTasaciones.controller;

import com.mercadolibre.tucasitaTasaciones.dto.PropertyDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.PropertyResponseDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;
import com.mercadolibre.tucasitaTasaciones.service.CalculateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

    CalculateService calculateService;

    public CalculateRestController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    /**
     * Calcula los metros cuadrados de una casa, su precio y su habitación mayor
     *
     * @param house
     * @return PropertyResponseDTO
     * @throws ExceptionLocationPriceIncorrect
     * @throws ExceptionLocationNotFound
     */
    @PostMapping("/calculate")
    public PropertyResponseDTO calculate(@Valid @RequestBody PropertyDTO house) throws ExceptionLocationPriceIncorrect, ExceptionLocationNotFound {
        return calculateService.calculate(house);
    }

}
