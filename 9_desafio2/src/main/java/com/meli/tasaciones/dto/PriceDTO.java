package com.meli.tasaciones.dto;

import lombok.Data;

@Data
public class PriceDTO {

  private String location;
  private double price;

  public PriceDTO(String location, double price) {
    this.location = location;
    this.price = price;
  }

  public PriceDTO() {
  }
}
