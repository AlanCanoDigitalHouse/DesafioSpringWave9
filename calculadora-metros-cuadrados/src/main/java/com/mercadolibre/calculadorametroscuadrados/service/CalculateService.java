package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dtos.*;
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

  /**
  Calculates: total house area, total house price, biggest room, and all room areas.
   Returns: HouseResponseDTO and HTTPStatus OK (200).
   */
  public ResponseEntity<HouseResponseDTO> allInOneCalculator(HouseRequestDTO houseReqDTO) {
    Double area = houseReqDTO.calculateHouseArea();
    if (!repo.locationExists(houseReqDTO.getDistrict_name()))
      throw new RuntimeException("Location does not exist in database");

    Double price = area * houseReqDTO.getDistrict_price();
    String biggestRoomName = houseReqDTO.getBiggestRoom().getEnvironment_name();
    List<RoomAreaDTO> room_areas = houseReqDTO.getRoomAreasDTOs();

    HouseResponseDTO houseResDTO = new HouseResponseDTO(
            houseReqDTO.getProp_name(),
            area,
            price,
            biggestRoomName,
            room_areas
    );
    return new ResponseEntity<>(houseResDTO, HttpStatus.OK);
  }

}
