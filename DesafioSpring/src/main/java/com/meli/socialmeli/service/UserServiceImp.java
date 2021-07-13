package com.meli.socialmeli.service;

import com.meli.socialmeli.model.User;
import com.meli.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

  private UserRepository repo;

  public UserServiceImp(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public void addFollower(Integer followerId, Integer followedId) {
    repo.addFollower(followerId, followedId);
  }

  @Override
  public Integer getFollowersCount(Integer userId) {
    return repo.getFollowersCount(userId);
  }

  @Override
  public User findUser(Integer userId) {
    return repo.findUser(userId);
  }

  @Override
  public List<User> findUsersFollowedBy(Integer userdId) {
    return repo.findUsersFollowedBy(userdId);
  }
}
