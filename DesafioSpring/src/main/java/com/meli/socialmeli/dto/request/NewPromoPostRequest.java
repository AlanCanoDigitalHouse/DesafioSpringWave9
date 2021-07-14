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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Builder
@Validated
public class NewPromoPostRequest {
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  private Integer userId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @PastOrPresent(message = SocialMeliConstants.VALIDATION_PAST_OR_PRESENT_MESSAGE)
  private LocalDate date;
  @Valid
  private ProductDTO detail;
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  private Integer category;
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  private Double price;
  @AssertTrue(message = SocialMeliConstants.VALIDATION_TRUE_MESSAGE)
  private Boolean hasPromo;
  @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE)
  private Double discount;


  public NewPromoPostRequest(Integer userId, LocalDate date, ProductDTO detail, Integer category, Double price) {
    this.userId = userId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
  }

  public NewPromoPostRequest(Integer userId, LocalDate date, ProductDTO detail, Integer category, Double price, Boolean hasPromo, Double discount) {
    this.userId = userId;
    this.date = date;
    this.detail = detail;
    this.category = category;
    this.price = price;
    this.hasPromo = hasPromo;
    this.discount = discount;
  }

  public NewPromoPostRequest() {
  }
}
