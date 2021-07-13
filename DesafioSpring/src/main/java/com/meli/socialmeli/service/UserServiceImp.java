package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.User;
import com.meli.socialmeli.repository.UserRepository;
import com.meli.socialmeli.util.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

  private Integer DEFAULT_OF_THE_LAST_DAYS = 14;

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

  @Override
  public void newPost(Integer userId, PostDTO post) {
    repo.newPost(userId, MapperUtils.dtoToPost(userId, post));
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId) {
    return repo.findPostsOfSellersFollowedBy(userId, DEFAULT_OF_THE_LAST_DAYS, Post::compareTo);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays) {
    return repo.findPostsOfSellersFollowedBy(userId, ofTheLastDays);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays, Comparator<Post> c) {
    return repo.findPostsOfSellersFollowedBy(userId, ofTheLastDays, c);
  }
}
