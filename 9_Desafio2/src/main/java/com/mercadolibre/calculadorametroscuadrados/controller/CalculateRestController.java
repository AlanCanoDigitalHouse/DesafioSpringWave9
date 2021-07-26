package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictsListDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseOkDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DistrictDoesntExistException;
import com.mercadolibre.calculadorametroscuadrados.service.ICalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculateRestController {


  ICalculateService calculateService;

  public CalculateRestController(ICalculateService calculateService){
    this.calculateService = calculateService;
  }

  @PostMapping("/calculate")
  public ResponseEntity<HouseResponseDTO> calculate(@Valid @RequestBody HouseDTO house) throws DistrictDoesntExistException {
    return new ResponseEntity<>(this.calculateService.calculate(house), HttpStatus.OK);
  }

  @PostMapping("/loadDistricts")
  public ResponseEntity<ResponseOkDTO> loadDistricts(@RequestBody DistrictsListDTO districtsListDTO){
    return new ResponseEntity<>(this.calculateService.loadDistricts(districtsListDTO.getDistricts()) , HttpStatus.OK);
  }

}
