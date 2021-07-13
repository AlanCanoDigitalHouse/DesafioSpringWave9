package com.meli.socialmeli.dto;

import com.meli.socialmeli.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
  private String productName;
  private String type;
  private String brand;
  private String color;
  private String notes;

  public ProductDTO(String productName, String type, String brand, String color, String notes) {
    this.productName = productName;
    this.type = type;
    this.brand = brand;
    this.color = color;
    this.notes = notes;
  }

  public ProductDTO(Product product) {
    this.productName = getProductName();
    this.type = product.getType();
    this.brand = product.getBrand();
    this.color = product.getColor();
    this.notes = product.getNotes();
  }
}
