package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.service.ICalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {
  @Autowired
  ICalculateService calculateService;
  @PostMapping("/calculate")
  public HouseResponseDTO calculate(@Valid @RequestBody HouseDTO house) throws DataNotFound {
    return calculateService.calculateHome(house);
  }
}
