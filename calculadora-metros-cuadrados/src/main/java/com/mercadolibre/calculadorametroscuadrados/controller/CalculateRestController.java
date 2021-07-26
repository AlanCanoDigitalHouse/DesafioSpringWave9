package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  @Autowired
  CalculateService calculateService;

  /**
   Get method. Receives a valid HouseRequestDTO in payload and returns a ResponseEntity with OK status, containing
   a HouseResponseDTO class object, with the following 4 calculations:
   - Total house squared metres
   - Total house price
   - Name of biggest room in house
   - List of rooms' areas with their names (List<RoomAreaDTO>)
   */
  @GetMapping("/calculate")
  public ResponseEntity<HouseResponseDTO> allInOneCalculator(@Valid @RequestBody HouseRequestDTO houseReqDTO) {
    return calculateService.allInOneCalculator(houseReqDTO);
  }
}
