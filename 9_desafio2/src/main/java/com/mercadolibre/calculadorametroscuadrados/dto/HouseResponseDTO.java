package com.mercadolibre.calculadorametroscuadrados.dto;

public class HouseResponseDTO extends HouseDTO {
  private Double squareFeet;
  private Double price;
  private RoomDTO biggest;

  public HouseResponseDTO() {
  }

  public HouseResponseDTO(HouseDTO house) {
    this.setProp_name(house.getProp_name());
    this.setDistrict_name(house.getDistrict_name());
    this.setEnvironments(house.getEnvironments());
  }

  public Double getSquareFeet() {
    return squareFeet;
  }

  public void setSquareFeet(Double squareFeet) {
    this.squareFeet = squareFeet;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public RoomDTO getBiggest() {
    return biggest;
  }

  public void setBiggest(RoomDTO biggest) {
    this.biggest = biggest;
  }
}
