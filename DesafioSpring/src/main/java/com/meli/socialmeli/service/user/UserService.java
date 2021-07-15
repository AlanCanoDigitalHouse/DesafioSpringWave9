package com.meli.socialmeli.service.user;

import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
  UserDTO getUser(Integer userId) throws UserNotFoundException;

  List<UserDTO> getUsersFollowedBy(Integer userdId) throws UserNotFoundException;

  List<UserDTO> getUsersFollowedBy(Integer userdId, String order) throws UserNotFoundException;

  List<UserDTO> getUserFollowers(Integer userdId) throws UserNotFoundException;

  List<UserDTO> getUserFollowers(Integer userdId, String order) throws UserNotFoundException;

  void addFollower(Integer followerId, Integer followedId) throws UserNotFoundException;

  void removeFollower(Integer followerId, Integer followedId) throws UserNotFoundException;

  Integer getFollowersCount(Integer userId) throws UserNotFoundException;
}
