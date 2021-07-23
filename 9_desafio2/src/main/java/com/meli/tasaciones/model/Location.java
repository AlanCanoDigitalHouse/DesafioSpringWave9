package com.meli.tasaciones.model;

import lombok.Data;
import lombok.Getter;

public class Location {

  private String location;
  private Double price;

  public Location(String location, Double price) {
    this.location = location;
    this.price = price;
  }

  public Double getPrice() {
    return price;
  }
}
