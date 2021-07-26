package com.tucasitatasaciones.controllers;

import com.tucasitatasaciones.DTOs.PropertyDTO;
import com.tucasitatasaciones.DTOs.PropertyResponseDTO;
import com.tucasitatasaciones.services.CalculateService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

    final CalculateService calculateService;

    public CalculateRestController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculate")
    public PropertyResponseDTO calculate(@Valid @RequestBody PropertyDTO house) {
        return calculateService.calculate(house);
    }

}
