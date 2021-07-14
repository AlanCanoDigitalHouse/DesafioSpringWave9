package com.meli.socialmeli.dto;

import lombok.Data;

@Data
public class UserPromoPostCountDTO {
  private Integer userId;
  private String userName;
  private Integer promoproducts_count;

  public UserPromoPostCountDTO(Integer userId, String userName, Integer promoproducts_count) {
    this.userId = userId;
    this.userName = userName;
    this.promoproducts_count = promoproducts_count;
  }
}
