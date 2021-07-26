package com.example.desafiotesting.dto;

public class HouseResponseDTO extends HouseDTO {
  private Integer squareFeet;
  private Integer district_price;
  private RoomDTO biggest;

  public HouseResponseDTO() {
  }

  public HouseResponseDTO(HouseDTO house) {
    this.setProp_name(house.getProp_name());
    this.setRooms(house.getRooms());
  }

  public Integer getSquareFeet() {
    return squareFeet;
  }

  public void setSquareFeet(Integer squareFeet) {
    this.squareFeet = squareFeet;
  }

  public Integer getDistrict_price() {
    return district_price;
  }

  public void setDistrict_price(Integer district_price) {
    this.district_price = district_price;
  }

  public RoomDTO getBiggest() {
    return biggest;
  }

  public void setBiggest(RoomDTO biggest) {
    this.biggest = biggest;
  }
}
