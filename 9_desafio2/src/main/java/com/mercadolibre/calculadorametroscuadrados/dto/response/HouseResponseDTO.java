package com.mercadolibre.calculadorametroscuadrados.dto.response;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;

public class HouseResponseDTO{
  private Double squareFeet;
  private Double price;
  private RoomDTO biggest;

  public HouseResponseDTO() {
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
