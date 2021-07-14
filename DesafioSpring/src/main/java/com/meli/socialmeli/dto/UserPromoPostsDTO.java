package com.meli.socialmeli.dto;

import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.util.MapperUtils;
import lombok.Data;

import java.util.List;

@Data
public class UserPromoPostsDTO {

  private Integer userId;
  private String userName;
  private List<PostDTO> posts;

  public UserPromoPostsDTO(Integer userId, String userName, List<Post> posts) {
    this.userId = userId;
    this.userName = userName;
    this.posts = MapperUtils.postDTOList(posts);
  }
}
