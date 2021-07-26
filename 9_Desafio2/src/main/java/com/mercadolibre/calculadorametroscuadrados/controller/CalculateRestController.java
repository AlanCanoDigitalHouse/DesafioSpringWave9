package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.service.IHouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController()
@RequestMapping("/challenge")
public class CalculateRestController {

  private IHouseService iHouseService;
  public CalculateRestController(IHouseService iHouseService) {  this.iHouseService = iHouseService;  }

  @PostMapping("/start")
  public ResponseEntity<String> start() {
    return new ResponseEntity<>(iHouseService.start(), HttpStatus.OK);
  }

  // US-0001
  @GetMapping("/calculate-area")
  public ResponseEntity<HouseResponseDTO> calculate(@Valid @RequestBody HouseTotalSizeRequestDTO house) {
    return new ResponseEntity<>(iHouseService.calculate(house), HttpStatus.OK);
  }

  // US-0002
  @GetMapping("/house-price")
  public ResponseEntity<HousePriceResponseDTO> totalPrice(@RequestBody HousePriceRequestDTO houseDTO){
    return new ResponseEntity<>(iHouseService.totalPrice(houseDTO), HttpStatus.OK);
  }

  // US-0003
  @GetMapping("/biggest-room")
  public ResponseEntity<RoomResponseBiggestDTO> biggest(@RequestBody HouseDTO houseDTO){
    return new ResponseEntity<>(iHouseService.biggestRoom(houseDTO), HttpStatus.OK);
  }

  // US-0004
  @GetMapping("/count-size")
  public ResponseEntity<HouseSizesResponseDTO> count(@RequestBody HouseDTO house){
    return new ResponseEntity<>(iHouseService.countSizes(house), HttpStatus.OK);
  }
}
