package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;

import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

  private final PriceRepository priceRepository;

  public CalculateServiceImpl(PriceRepository priceRepository){
    this.priceRepository = priceRepository;
  }

  @Override
  public HouseResponseDTO calculate(HouseDTO house) throws NotFoundLocation {
    HouseResponseDTO response = new HouseResponseDTO(house);
    calculateRoomSquareFeet(house, response);
    response.setPrice(calculatePrice(response.getSquareFeet(), house));
    return response;
  }

  private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
    Double totalSquareFeet = 0d;
    RoomDTO biggest = null;
    Double maxRoom = 0d;
    for (RoomDTO room : house.getEnvironment()) {
      room.setSquareFeet(room.getWidth()*room.getLength());
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

  private Double calculatePrice(Double result, HouseDTO house) throws NotFoundLocation {
    PriceDTO priceDTO = priceRepository.findLocation(house.getDistrictName());
    return result * house.getDistrictPrice();
  }

}
