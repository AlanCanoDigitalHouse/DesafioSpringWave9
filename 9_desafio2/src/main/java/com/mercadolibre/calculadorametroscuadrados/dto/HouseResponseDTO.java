package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HouseResponseDTO extends HouseDTO {
  private Double squareFeet;
  private Double price;
  private RoomDTO biggest;

  public HouseResponseDTO(HouseDTO house) {
    this.setProp_name(house.getProp_name());
    this.setDistrict_name(house.getDistrict_name());
    this.setDistrict_price(house.getDistrict_price());
    this.setRooms(house.getRooms());
  }

}
