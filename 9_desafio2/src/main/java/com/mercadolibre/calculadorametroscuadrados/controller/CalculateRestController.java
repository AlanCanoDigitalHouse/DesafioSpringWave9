package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping()
public class CalculateRestController {

  CalculateService calculateService;


  public CalculateRestController(CalculateService calculateService) {
    this.calculateService = calculateService;
  }

  @PostMapping("/calculate")
  public ResponseEntity<HouseResponseDTO> calculate(@Valid @RequestBody HouseDTO house){
    return new ResponseEntity<>(calculateService.calculate(house), HttpStatus.OK);
  }
}
