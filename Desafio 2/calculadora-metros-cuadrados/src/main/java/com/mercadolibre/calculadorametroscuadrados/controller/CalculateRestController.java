package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.responses.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  CalculateService calculateService = new CalculateService();

  @PostMapping("/calculate")
  public ResponseEntity<HouseResponseDTO> calculateAll(@RequestBody HouseDTO property){
    if ( property!= null && DistrictRepository.getInstance().existDistrict(property.getDistrict_name())) {
      HouseResponseDTO res = calculateService.mapResultado(property);
      return new ResponseEntity<>(res, HttpStatus.OK);
    }
    return new ResponseEntity<>( null , HttpStatus.BAD_REQUEST);
  }


}
