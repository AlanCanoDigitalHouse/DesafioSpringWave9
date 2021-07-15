package com.meli.socialmeli.dto;

import com.meli.socialmeli.model.Product;
import com.meli.socialmeli.util.constants.SocialMeliConstants;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@Validated
public class ProductDTO {
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  private Integer product_id;
  @NotBlank(message = SocialMeliConstants.VALIDATION_NOT_BLANK_MESSAGE)
  private String productName;
  @NotBlank(message = SocialMeliConstants.VALIDATION_NOT_BLANK_MESSAGE)
  private String type;
  @NotBlank(message = SocialMeliConstants.VALIDATION_NOT_BLANK_MESSAGE)
  private String brand;
  @NotBlank(message = SocialMeliConstants.VALIDATION_NOT_BLANK_MESSAGE)
  private String color;
  @NotBlank(message = SocialMeliConstants.VALIDATION_NOT_BLANK_MESSAGE)
  private String notes;

  public ProductDTO(String productName, String type, String brand, String color, String notes) {
    this.productName = productName;
    this.type = type;
    this.brand = brand;
    this.color = color;
    this.notes = notes;
  }

  public ProductDTO(Integer product_id, String productName, String type, String brand, String color, String notes) {
    this.product_id = product_id;
    this.productName = productName;
    this.type = type;
    this.brand = brand;
    this.color = color;
    this.notes = notes;
  }

  public ProductDTO(Product product) {
    this.product_id = product.getProductId();
    this.productName = getProductName();
    this.type = product.getType();
    this.brand = product.getBrand();
    this.color = product.getColor();
    this.notes = product.getNotes();
  }

  public ProductDTO() {
  }
}
