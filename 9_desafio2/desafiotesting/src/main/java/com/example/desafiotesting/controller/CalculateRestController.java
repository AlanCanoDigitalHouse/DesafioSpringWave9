package com.example.desafiotesting.controller;


import com.example.desafiotesting.dto.HouseDTO;
import com.example.desafiotesting.dto.HouseResponseDTO;
import com.example.desafiotesting.dto.PriceDTO;
import com.example.desafiotesting.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  @Autowired
  CalculateService calculateService;

  @PostMapping("/calculate")
  public HouseResponseDTO calculate(@Valid @RequestBody HouseDTO house){
    return calculateService.calculate(house);
  }

  @GetMapping("/{location}")
  public PriceDTO calculateLocation(@Valid @PathVariable String location){return calculateService.probarMapper(location);
  }
}
