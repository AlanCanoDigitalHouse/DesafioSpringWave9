package com.meli.socialmeli.util;

import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

  public static List<UserDTO> usersToDTO(Collection<User> users) {
    return users.stream().map(user -> new UserDTO(user.getUserId(), user.getUserName())).collect(Collectors.toCollection(ArrayList::new));
  }
}
