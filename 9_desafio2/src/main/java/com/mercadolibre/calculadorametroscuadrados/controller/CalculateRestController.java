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

  /**
   * TODO: US-0001 US-0002 US-0003 US-0004
   * ● US-0001: Calcular el total de metros cuadrados de una propiedad
   * ● US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.
   * ● US-0003: Determinar cuál es el ambiente más grande.
   * ● US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.
   * @param house
   * @return
   */
  @PostMapping("/calculate")
  public ResponseEntity<HouseResponseDTO> calculate(@Valid @RequestBody HouseDTO house){
    return new ResponseEntity<>(calculateService.calculate(house), HttpStatus.OK);
  }
}
