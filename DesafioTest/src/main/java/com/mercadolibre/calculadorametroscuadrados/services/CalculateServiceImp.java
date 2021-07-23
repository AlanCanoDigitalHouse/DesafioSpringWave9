package com.mercadolibre.calculadorametroscuadrados.services;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImp implements CalculateService {

  DistrictRepository priceRepository;

  public CalculateServiceImp(DistrictRepository priceRepository){
    this.priceRepository = priceRepository;
  }

  @Override
  public HouseDTO calculate(HouseDTO house) {
    priceRepository.findDistrictByDistrictName(house.getDistrictName());
    calculateRoomSquareMeter(house);
    return house;
  }

  private void calculateRoomSquareMeter(HouseDTO house) {
    Double totalSquareMeter = 0.0;
    RoomDTO biggest = null;
    Double maxRoom = 0.0;
    for (RoomDTO room : house.getEnvironments()) {
      Double squareMeter = room.calculateSquareMeter();
      totalSquareMeter += squareMeter;
      if (biggest == null || squareMeter > maxRoom){
        biggest = room;
        maxRoom = squareMeter;
      }
    }
    house.setTotalSquareMeter(totalSquareMeter);
    house.setBiggest(biggest);
    house.setPrice(house.getDistrictPrice()*totalSquareMeter);
  }





}
