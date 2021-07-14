package com.meli.socialmeli.dto;

import lombok.Data;

@Data
public class UserFollowersCountDTO {
  private Integer userId;
  private String userName;
  private Integer followers_count;

  public UserFollowersCountDTO(Integer userId, String userName, Integer followers_count) {
    this.userId = userId;
    this.userName = userName;
    this.followers_count = followers_count;
  }
}
