package com.meli.socialmeli.service;

import com.meli.socialmeli.model.User;

import java.util.List;

public interface UserService {
  public void addFollower(Integer followerId, Integer followedId);

  public Integer getFollowersCount(Integer userId);

  public User findUser(Integer userId);

  public List<User> findUsersFollowedBy(Integer userdId);

}
