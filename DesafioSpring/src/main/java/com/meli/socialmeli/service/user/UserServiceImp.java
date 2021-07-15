package com.meli.socialmeli.service.user;

import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.model.User;
import com.meli.socialmeli.repository.user.UserRepository;
import com.meli.socialmeli.service.follow.FollowService;
import com.meli.socialmeli.util.MapperUtils;
import com.meli.socialmeli.util.UserComparatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
  @Autowired
  UserRepository repo;
  @Autowired
  FollowService followService;

  @Override
  public UserDTO getUser(Integer userId) throws UserNotFoundException {
    User user = repo.getUser(userId);
    return MapperUtils.userToDto(user);
  }

  public User getUserEntity(Integer userId) throws UserNotFoundException {
    return repo.getUser(userId);
  }

  @Override
  public List<UserDTO> getUsersFollowedBy(Integer userdId) throws UserNotFoundException {
    List<User> followers = getFollowedBy(userdId);
    return MapperUtils.usersToDTOs(followers);
  }

  @Override
  public List<UserDTO> getUsersFollowedBy(Integer userdId, String order) throws UserNotFoundException {
    List<User> followers = getFollowedBy(userdId);
    followers.sort(UserComparatorFactory.getComparator(order));
    return MapperUtils.usersToDTOs(followers);
  }

  @Override
  public List<UserDTO> getUserFollowers(Integer userdId) throws UserNotFoundException {
    List<User> followers = getFollowers(userdId);
    return MapperUtils.usersToDTOs(followers);
  }

  @Override
  public List<UserDTO> getUserFollowers(Integer userdId, String order) throws UserNotFoundException {
    List<User> followers = getFollowers(userdId);
    followers.sort(UserComparatorFactory.getComparator(order));
    return MapperUtils.usersToDTOs(followers);
  }

  @Override
  public void addFollower(Integer followerId, Integer followedId) throws UserNotFoundException {
    getUser(followerId);
    getUser(followedId);
    followService.addFollower(followerId, followedId);
  }

  @Override
  public void removeFollower(Integer followerId, Integer followedId) throws UserNotFoundException {
    followService.removeFollower(followerId, followedId);
  }

  @Override
  public Integer getFollowersCount(Integer userId) throws UserNotFoundException {
    List<User> followers = getFollowers(userId);
    return followers.size();
  }

  private List<User> getFollowers(Integer userId) throws UserNotFoundException {
    getUser(userId);
    List<Integer> followerKeys = followService.getFollowers(userId);
    return findUsers(followerKeys);
  }

  private List<User> getFollowedBy(Integer userId) throws UserNotFoundException {
    getUser(userId);
    List<Integer> followerKeys = followService.getFollowedBy(userId);
    return findUsers(followerKeys);
  }

  private List<User> findUsers(List<Integer> userIds) throws UserNotFoundException {
    ArrayList<User> users = new ArrayList<>();
    for (Integer id : userIds) {
      users.add(getUserEntity(id));
    }
    return users;
  }
}
