package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.repositories.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateService {

  LocationRepository repo;

  public CalculateService(LocationRepository priceRepository){
    this.repo = priceRepository;
  }

  public ResponseEntity<HouseResponseDTO> allInOneCalculator(HouseRequestDTO houseReqDTO) {
    Double area = houseReqDTO.calculateHouseSquareFeet();
    if (repo.locationExists(houseReqDTO.getDistrict_name()))
      throw new RuntimeException("Location does not exist in database");

    Double price = area * houseReqDTO.getDistrict_price();
    String biggestHouseName = houseReqDTO.getBiggestRoom().getEnvironment_name();
    List<RoomAreaDTO> room_areas = houseReqDTO.getRoomAreasDTOs();

    HouseResponseDTO houseResDTO = new HouseResponseDTO(
            houseReqDTO.getProp_name(),
            area,
            price,
            biggestHouseName,
            room_areas
    );
    return new ResponseEntity<>(houseResDTO, HttpStatus.OK);
  }

}
