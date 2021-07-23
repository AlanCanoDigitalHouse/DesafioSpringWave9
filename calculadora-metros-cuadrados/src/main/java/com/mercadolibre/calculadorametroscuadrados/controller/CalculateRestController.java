package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  @Autowired
  CalculateService calculateService;

  @GetMapping("/calculations")
  public ResponseEntity<HouseResponseDTO> allInOneCalculator(@Valid @RequestBody HouseRequestDTO houseReqDTO) {
    return calculateService.allInOneCalculator(houseReqDTO);
  }
}
