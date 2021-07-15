package com.meli.socialmeli.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.meli.socialmeli.dto.ProductDTO;
import com.meli.socialmeli.util.constants.SocialMeliConstants;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Builder
@Validated
public class NewPostRequest {

  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  @NotNull(message = SocialMeliConstants.VALIDATION_NOT_NULL_MESSAGE)
  private Integer userId;
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  @NotNull(message = SocialMeliConstants.VALIDATION_NOT_NULL_MESSAGE)
  private Integer id_post;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @FutureOrPresent(message = SocialMeliConstants.VALIDATION_FUTURE_OR_PRESENT_MESSAGE)
  private LocalDate date;
  @Valid
  private ProductDTO detail;
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  @NotNull(message = SocialMeliConstants.VALIDATION_NOT_NULL_MESSAGE)
  private Integer category;
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  @NotNull(message = SocialMeliConstants.VALIDATION_NOT_NULL_MESSAGE)
  private Double price;
  @NotNull(message = SocialMeliConstants.VALIDATION_NOT_NULL_MESSAGE)
  private Boolean hasPromo;
  @NotNull(message = SocialMeliConstants.VALIDATION_NOT_NULL_MESSAGE)
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

  public NewPostRequest(Integer userId, Integer id_post, LocalDate date, ProductDTO detail, Integer category, Double price, Boolean hasPromo, Double discount) {
    this.userId = userId;
    this.id_post = id_post;
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
