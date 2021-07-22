package com.tucasitatasaciones.controllers;

import com.tucasitatasaciones.DTOs.PropertyDTO;
import com.tucasitatasaciones.DTOs.PropertyResponseDTO;
import com.tucasitatasaciones.DTOs.PriceDTO;
import com.tucasitatasaciones.services.CalculateService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculateRestController {

    final CalculateService calculateService;

    public CalculateRestController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculate")
    public PropertyResponseDTO calculate(@RequestBody PropertyDTO house) {
        return calculateService.calculate(house);
    }

    @GetMapping("/{location}")
    public PriceDTO calculateLocation(@PathVariable String location) {
        return calculateService.probarMapper(location);
    }
}
