package com.mercadolibre.calculadorametroscuadrados.dto.response;

import com.mercadolibre.calculadorametroscuadrados.dto.EnviromentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HouseResponseDTO extends HouseDTO {
  private Double squareFeet;
  private Double price;
  private EnviromentDTO biggest;

  public HouseResponseDTO(HouseDTO house) {
    this.setName(house.getName());
    this.setDistrict(house.getDistrict());
    this.setEnviroments(house.getEnviroments());
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

  public EnviromentDTO getBiggest() {
    return biggest;
  }

  public void setBiggest(EnviromentDTO biggest) {
    this.biggest = biggest;
  }
}
