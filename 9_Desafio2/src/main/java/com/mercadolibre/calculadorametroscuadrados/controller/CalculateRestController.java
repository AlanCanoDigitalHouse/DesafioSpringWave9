package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  private final CalculateService calculateService;

  public CalculateRestController(CalculateService calculateService) {
    this.calculateService = calculateService;
  }

  /**
   * US-0001,US-0002,US-0003,US-0004: Metodo para realizar calculos de la propiedad.
   * @param house post para realizar calculos de la propiedad.
   * @return El resultado de los calculos sobre la propiedad
   * @throws NotFoundLocation Si el districto ingresado no existe
   */
  @PostMapping("/calculate")
  public HouseResponseDTO calculate(@RequestBody @Valid HouseDTO house) throws NotFoundLocation {
    return calculateService.calculate(house);
  }

}
