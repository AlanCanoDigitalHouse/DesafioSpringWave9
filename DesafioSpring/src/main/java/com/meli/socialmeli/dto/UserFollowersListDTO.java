package com.meli.socialmeli.dto;

import com.meli.socialmeli.model.User;
import com.meli.socialmeli.util.MapperUtils;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class UserFollowersListDTO {
  private Integer userId;
  private String userName;
  private List<UserDTO> followers;

  public UserFollowersListDTO(Integer userId, String userName, Collection<User> users) {
    this.userId = userId;
    this.userName = userName;
    this.followers = MapperUtils.usersToDTO(users);
  }

}
