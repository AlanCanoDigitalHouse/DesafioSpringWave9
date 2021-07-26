package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  CalculateService calculateService;

  public CalculateRestController(CalculateService calculateService) {
    this.calculateService = calculateService;
  }

  @PostMapping(value = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public HouseResponseDTO calculate(@Valid @RequestBody HouseDTO house){
    return calculateService.calculate(house);
  }
}
