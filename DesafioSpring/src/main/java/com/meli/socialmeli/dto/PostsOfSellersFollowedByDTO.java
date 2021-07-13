package com.meli.socialmeli.dto;

import com.meli.socialmeli.model.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostsOfSellersFollowedByDTO {
  private Integer userId;
  private List<PostDTO> posts;

  public PostsOfSellersFollowedByDTO(Integer userId, List<PostDTO> posts) {
    this.userId = userId;
    this.posts = posts;
  }
}
