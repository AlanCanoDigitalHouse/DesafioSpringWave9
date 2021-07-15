package com.meli.socialmeli.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPromoPostsDTO {

  private Integer userId;
  private String userName;
  private List<PostDTO> posts;

  public UserPromoPostsDTO(Integer userId, String userName, List<PostDTO> posts) {
    this.userId = userId;
    this.userName = userName;
    this.posts = posts;
  }
}
