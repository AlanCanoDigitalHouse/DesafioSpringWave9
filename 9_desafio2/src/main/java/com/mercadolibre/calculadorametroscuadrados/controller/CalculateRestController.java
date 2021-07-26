package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import com.mercadolibre.calculadorametroscuadrados.services.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  @Autowired
  CalculateService calculateService;


  /**
   * Punto 1: Returns a HouseResponseDTO with the information of its HouseRequest.
   * @param house
   * @return HouseResponseDto
   * @throws LocationNotFound IncorrectLocationPrice
   */
  @GetMapping("/calculate")
  public HouseResponseDTO calculate(@Valid @RequestBody HouseRequestDTO house) throws LocationNotFound, IncorrectLocationPrice {
    return calculateService.calculate(house);
  }
}
