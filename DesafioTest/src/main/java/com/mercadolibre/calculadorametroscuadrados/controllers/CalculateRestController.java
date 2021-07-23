package com.mercadolibre.calculadorametroscuadrados.controllers;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.CalculateExceptionController;
import com.mercadolibre.calculadorametroscuadrados.services.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  CalculateService calculateService;

  public CalculateRestController(CalculateService calculateService) {
    this.calculateService = calculateService;
  }

  /**
   * Obtener el total de metros cuadrados de una propiedad,
   * valor de la propiedad en función a los ambientes y medidas,
   * ambiente más grande y metros cuadrados por ambiente.
   * @param house propiedad con ambientes.
   * @return un objeto HouseDTO con el valor de la propiedad, total metros cuadrados,
   * ambiente mas grande y metros cuadrados por ambientes.
   * @throws CalculateExceptionController
   */
  @PostMapping("/calculate")
  public ResponseEntity<HouseDTO> calculate(@Valid @RequestBody HouseDTO house){
    return new ResponseEntity<>(calculateService.calculate(house), HttpStatus.OK);
  }

}
