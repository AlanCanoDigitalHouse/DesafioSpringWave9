package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseOkDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DistrictDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateService implements ICalculateService{

  private List<String> districtsList;

  public CalculateService(){
    this.districtsList = new ArrayList<>();
  }

  @Override
  public HouseResponseDTO calculate(HouseDTO house) throws DistrictDoesntExistException {
      checkIfDistrictExist(house.getDistrict().getName());
      HouseResponseDTO response = new HouseResponseDTO(house);
      calculateRoomSquareFeet(house, response);
      this.calculatePrice(response);
      return response;
  }

  @Override
  public ResponseOkDTO loadDistricts (List<String> districtsList){
    this.districtsList = districtsList;
    return new ResponseOkDTO(HttpStatus.OK.value(), "Lista de distritos cargada con exito");
  }

  private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
    Double totalSquareFeet = 0D;
    EnviromentDTO biggest = null;
    Double maxRoom = 0D;
    for (EnviromentDTO room : house.getEnviroments()) {
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

  private void calculatePrice(HouseResponseDTO response) {
    response.setPrice(response.getSquareFeet() * response.getDistrict().getPrice());
  }

  private void checkIfDistrictExist(String district) throws DistrictDoesntExistException {
    if (!this.districtsList.contains(district)){
      throw new DistrictDoesntExistException(district);
    }
  }
}
