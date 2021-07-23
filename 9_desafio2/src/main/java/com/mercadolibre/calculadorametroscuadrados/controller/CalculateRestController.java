package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  private CalculateService calculateService;

  public CalculateRestController(CalculateService calculateService) {
    this.calculateService = calculateService;
  }

  @PostMapping("/calculate")
  public HouseResponseDTO calculate(@RequestBody @Valid HouseDTO house){
    return calculateService.calculate(house);
  }
}
