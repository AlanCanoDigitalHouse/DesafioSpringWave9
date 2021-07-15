package com.meli.socialmeli.repository.follow;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class FollowRepositoryImp implements FollowRepository {
  private final Set<Follow> follows;

  public FollowRepositoryImp(Set<Follow> follows) {
    this.follows = follows;
  }

  public FollowRepositoryImp() {
    this.follows = loadFollowers();
  }

  @Override
  public void addFollower(Integer followerId, Integer followedId) {
    if (!followerId.equals(followedId))
      follows.add(new Follow(followerId, followedId));
  }

  @Override
  public void removeFollower(Integer followerId, Integer followedId) {
    follows.remove(new Follow(followerId, followedId));
  }

  @Override
  public List<Integer> getFollowers(Integer userId) {
    return follows.stream().filter(follow -> follow.getFollowedKey().equals(userId)).map(Follow::getFollowerKey).collect(Collectors.toList());
  }

  @Override
  public List<Integer> getFollowedBy(Integer userId) {
    return follows.stream().filter(follow -> follow.getFollowerKey().equals(userId)).map(Follow::getFollowedKey).collect(Collectors.toList());
  }

  @Override
  public Integer getFollowersCount(Integer userId) {
    List<Integer> followers = getFollowers(userId);
    return followers.size();
  }

  private Set<Follow> loadFollowers() {
    HashSet<Follow> follows = new HashSet<>();
    follows.add(new Follow(1, 2));
    follows.add(new Follow(3, 2));
    follows.add(new Follow(1, 3));
    return follows;
  }
}
