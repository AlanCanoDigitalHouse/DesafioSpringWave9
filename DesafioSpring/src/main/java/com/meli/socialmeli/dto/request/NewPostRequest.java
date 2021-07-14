package com.meli.socialmeli.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.meli.socialmeli.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NewPostRequest {
  private Integer userId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate date;
  private ProductDTO detail;
  private Integer category;
  private Double price;
  private Boolean hasPromo;
  private Double discount;

  public NewPostRequest(Integer userId, LocalDate date, ProductDTO detail, Integer category, Double price) {
    this.userId = userId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
  }

  public NewPostRequest(Integer userId, LocalDate date, ProductDTO detail, Integer category, Double price, Boolean hasPromo, Double discount) {
    this.userId = userId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
    this.hasPromo = hasPromo;
    this.discount = discount;
  }

  public NewPostRequest() {
  }
}
