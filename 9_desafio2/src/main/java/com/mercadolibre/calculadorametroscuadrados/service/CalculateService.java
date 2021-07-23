package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFound;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictPriceNotMatch;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CalculateService {

  private final DistrictRepositoryImpl repository;

  public CalculateService(DistrictRepositoryImpl repository) {
    this.repository = repository;
  }

  public HouseResponseDTO calculate(HouseDTO house) throws DistrictNotFound, DistrictPriceNotMatch {
    HouseResponseDTO response = new HouseResponseDTO(house);
    calculateRoomSquareFeet(house, response);
    response.setPrice(calculatePrice(response.getSquareFeet(), response.getDistrict_price(), response.getDistrict_name()));
    return response;
  }

  private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
    Integer totalSquareFeet = 0;
    RoomDTO biggest = null;
    Integer maxRoom = 0;
    for (RoomDTO room : house.getEnviroments()) {
      Integer squareFeet = room.getSquareFeet();
      totalSquareFeet += squareFeet;
      if (biggest == null || squareFeet > maxRoom){
        biggest = room;
        maxRoom = squareFeet;
      }
    }
    response.setSquareFeet(totalSquareFeet);
    response.setBiggest(biggest);
  }

  private Double calculatePrice(Integer squareFeet, Double price, String district) throws DistrictNotFound, DistrictPriceNotMatch {
    Double priceProp = null;
    Double pricebyDistric = repository.findDistrictPrice(district);
    if (pricebyDistric.equals(price)){
      priceProp = squareFeet * price;
    }else {
      throw new DistrictPriceNotMatch();
    }
    return priceProp;
  }

}
