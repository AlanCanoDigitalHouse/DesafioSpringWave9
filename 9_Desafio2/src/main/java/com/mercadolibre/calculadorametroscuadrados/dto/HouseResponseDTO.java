package com.mercadolibre.calculadorametroscuadrados.dto;


import lombok.Data;

@Data
public class HouseResponseDTO extends HouseDTO {
  private Double squareFeet;
  private Double price;
  private RoomDTO biggest;

  public HouseResponseDTO(HouseDTO house) {
    this.setPropName(house.getPropName());
    this.setDistrictName(house.getDistrictName());
    this.setDistrictPrice(house.getDistrictPrice());
    this.setEnvironment(house.getEnvironment());
  }

}
