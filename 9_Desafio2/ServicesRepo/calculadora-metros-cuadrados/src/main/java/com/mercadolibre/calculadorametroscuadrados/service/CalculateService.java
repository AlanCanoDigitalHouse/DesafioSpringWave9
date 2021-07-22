package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
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
    response.setPrice(calculatePrice(response.getSquareFeet()));
    return response;
  }

  private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
    Integer totalSquareFeet = 0;
    RoomDTO biggest = null;
    Integer maxRoom = 0;
    for (RoomDTO room : house.getRooms()) {
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

  private int calculatePrice(Integer result) {
    return result * 800;
  }

  public PriceDTO probarMapper(String location){
    return  priceRepository.findPriceLocation(location);
  }
}
