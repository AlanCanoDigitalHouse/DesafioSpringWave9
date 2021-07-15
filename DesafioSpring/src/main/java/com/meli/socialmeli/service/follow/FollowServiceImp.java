package com.meli.socialmeli.service.follow;

import com.meli.socialmeli.repository.follow.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImp implements FollowService {
  @Autowired
  FollowRepository repo;

  @Override
  public void addFollower(Integer followerId, Integer followedId) {
    repo.addFollower(followerId, followedId);
  }

  @Override
  public void removeFollower(Integer followerId, Integer followedId) {
    repo.removeFollower(followerId, followedId);
  }

  @Override
  public List<Integer> getFollowers(Integer userId) {
    return repo.getFollowers(userId);
  }

  @Override
  public List<Integer> getFollowedBy(Integer userId) {
    return repo.getFollowedBy(userId);
  }

  @Override
  public Integer getFollowersCount(Integer userId) {
    return repo.getFollowersCount(userId);
  }
}
