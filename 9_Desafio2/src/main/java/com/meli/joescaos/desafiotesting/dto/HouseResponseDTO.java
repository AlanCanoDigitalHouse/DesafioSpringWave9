package com.meli.joescaos.desafiotesting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HouseResponseDTO extends HouseDTO {
  private Integer squareFeet;
  private Double price;
  private RoomDTO biggest;


  public HouseResponseDTO(HouseDTO house) {
    this.setProp_name(house.getProp_name());
    this.setDistrict_name(house.getDistrict_name());
    this.setRooms(house.getRooms());
    this.setDistrict_price(house.getDistrict_price());
  }

}
