package com.meli.socialmeli.dto;

import com.meli.socialmeli.model.User;
import com.meli.socialmeli.util.MapperUtils;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class UsersFollowedByListDTO {
  private Integer userId;
  private String userName;
  private List<UserDTO> followed;

  public UsersFollowedByListDTO(Integer userId, String userName, Collection<User> followed) {
    this.userId = userId;
    this.userName = userName;
    this.followed = MapperUtils.usersToDTO(followed);
  }
}
