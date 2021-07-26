package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

  PriceRepositoryImpl priceRepository;

  public CalculateService(PriceRepositoryImpl priceRepository){
    this.priceRepository = priceRepository;
  }

  public HouseResponseDTO calculate(HouseDTO house) {
    HouseResponseDTO response = new HouseResponseDTO(house);
    calculateRoomSquareFeet(house, response);
    response.setPrice(calculatePrice(response.getSquareFeet(), house.getDistrict_price(), house.getDistrict_name()));
    return response;
  }

  private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
    Double totalSquareFeet = 0.0;
    RoomDTO biggest = null;
    Double maxRoom = 0.0;
    for (RoomDTO room : house.getRooms()) {
      Double squareFeet = room.getSquareFeet();
      totalSquareFeet += squareFeet;
      if (biggest == null || squareFeet > maxRoom){
        biggest = room;
        maxRoom = squareFeet;
      }
    }
    response.setSquareFeet(totalSquareFeet);
    response.setBiggest(biggest);
  }

  private Double calculatePrice(Double result, Double price,  String district_name) {
    Boolean districtExist = priceRepository.findDistrictByName(district_name);
    return result * price;
  }
}
