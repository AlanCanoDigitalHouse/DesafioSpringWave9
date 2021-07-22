package com.meli.joescaos.desafiotesting.controllers;

import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.HouseResponseDTO;
import com.meli.joescaos.desafiotesting.dto.PriceDTO;
import com.meli.joescaos.desafiotesting.services.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculateRestController {

    @Autowired
    CalculateService calculateService;

    @PostMapping("/calculate")
    public HouseResponseDTO calculate(@RequestBody HouseDTO house){
        return calculateService.calculate(house);
    }

    @GetMapping("/{location}")
    public PriceDTO calculateLocation(@PathVariable String location){
        return calculateService.probarMapper(location);
    }
}
