package com.meli.socialmeli.repository;

import com.meli.socialmeli.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {

  private List<User> users;

  public UserRepositoryImp(List<User> users) {
    this.users = users;
  }

  public UserRepositoryImp() {
    this.users = loadUsers();
  }

  @Override
  public void addFollower(Integer followerId, Integer followedId) {
    Optional<User> optionalFollower = findUserById(followerId);
    Optional<User> optionalFollowed = findUserById(followedId);
    if (optionalFollower.isPresent() && optionalFollowed.isPresent()) {
      User followed = optionalFollowed.get();
      User follower = optionalFollower.get();
      followed.getFolllowers().add(follower);
    }
  }

  @Override
  public Integer getFollowersCount(Integer userId) {
    Optional<User> user = findUserById(userId);
    return user.isPresent() ? user.get().getFolllowers().size() : 0;
  }

  @Override
  public User findUser(Integer userId) {
    Optional<User> userById = findUserById(userId);
    return userById.orElse(null);
  }

  @Override
  public List<User> findUsersFollowedBy(Integer userdId) {
    //iterar a los usuarios y extraer los que tengan como seguidor al userId
    ArrayList<User> sellersFollowedByUser = new ArrayList<>();
    for (User seller : users) {
      for (User follower : seller.getFolllowers()) {
        if (follower.getUserId().equals(userdId)) {
          sellersFollowedByUser.add(seller);
        }
      }
    }
    return sellersFollowedByUser;
  }

  private Optional<User> findUserById(Integer userId) {
    return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
  }

  private List<User> loadUsers() {
    ArrayList<User> users = new ArrayList<>();
    User emilio = new User(1, "emilio");
    User daniel = new User(2, "daniel");
    daniel.getFolllowers().add(emilio);
    users.add(emilio);
    users.add(daniel);
    users.add(new User(3, "ofe"));
    return users;
  }
}
