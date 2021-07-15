package com.meli.socialmeli.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
  private Integer productId;
  private String productName;
  private String type;
  private String brand;
  private String color;
  private String notes;


  public Product(Integer productId, String productName, String type, String brand, String color, String notes) {
    this.productId = productId;
    this.productName = productName;
    this.type = type;
    this.brand = brand;
    this.color = color;
    this.notes = notes;
  }

  public Product(String productName, String type, String brand, String color, String notes) {
    this.productName = productName;
    this.type = type;
    this.brand = brand;
    this.color = color;
    this.notes = notes;
  }
}
