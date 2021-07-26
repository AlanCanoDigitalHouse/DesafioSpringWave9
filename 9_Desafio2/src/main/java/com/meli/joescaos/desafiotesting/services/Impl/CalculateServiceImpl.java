package com.meli.joescaos.desafiotesting.services.Impl;

import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.HouseResponseDTO;
import com.meli.joescaos.desafiotesting.dto.RoomDTO;
import com.meli.joescaos.desafiotesting.exceptions.PriceErrorException;
import com.meli.joescaos.desafiotesting.repositories.PriceRepository;
import com.meli.joescaos.desafiotesting.services.CalculateService;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

  PriceRepository priceRepository;

  public CalculateServiceImpl(PriceRepository priceRepository){
    this.priceRepository = priceRepository;
  }

  public HouseResponseDTO calculate(HouseDTO house) {
    priceRepository.districtExists(house.getDistrict_name());
    HouseResponseDTO response = new HouseResponseDTO(house);
    calculateRoomSquareFeet(house, response);
    response.setPrice(calculatePrice(response.getSquareFeet(), house));
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

  private double calculatePrice(Integer result, HouseDTO house) {
    double priceInDB = priceRepository.getDistrictPrice(house.getDistrict_name());
    double housePrice = house.getDistrict_price();
    if(priceInDB == housePrice )
      return result * priceInDB;
    else
      throw new PriceErrorException();
  }

}
