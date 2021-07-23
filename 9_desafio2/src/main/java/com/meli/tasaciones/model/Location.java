package com.meli.tasaciones.model;

import lombok.Data;

@Data
public class Location {

  private String location;
  private Double price;

  public Location(String location, Double price) {
    this.location = location;
    this.price = price;
  }
}
