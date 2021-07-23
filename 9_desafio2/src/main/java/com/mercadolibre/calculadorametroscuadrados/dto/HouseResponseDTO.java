package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.*;

import java.util.HashMap;

@Data
@Getter
@Setter
public class HouseResponseDTO extends HouseDTO {
  private Integer squareFeet;
  private Double price;
  private RoomDTO biggest;

  public HouseResponseDTO(HouseDTO house) {
    this.setProp_name(house.getProp_name());
    this.setDistrict_name(house.getDistrict_name());
    this.setDistrict_price(house.getDistrict_price());
    this.setEnviroments(house.getEnviroments());
  }

}
