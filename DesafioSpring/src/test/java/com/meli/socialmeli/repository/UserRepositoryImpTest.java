package com.meli.socialmeli.repository;

import com.meli.socialmeli.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UserRepositoryImpTest {

  UserRepository repo;

  @BeforeEach
  public void setUp() {
    ArrayList<User> users = new ArrayList<>();
    User emilio = new User(1, "emilio");
    User daniel = new User(2, "daniel");
    daniel.getFolllowers().add(emilio);
    users.add(emilio);
    users.add(daniel);
    users.add(new User(3, "ofe"));
    repo = new UserRepositoryImp(users);
  }

  @Test
  void addFollower() {
  }

  @Test
  void getFollowersCount() {
  }

  @Test
  void addFollowerAndTestFollowersCount() {
    Assertions.assertEquals(repo.getFollowersCount(2), 1);
    Assertions.assertEquals(repo.getFollowersCount(1), 0);
    repo.addFollower(3,2);
    Assertions.assertEquals(repo.getFollowersCount(2), 2);
  }

  @Test
  void findUserTest(){
    User user = repo.findUser(2);
    Assertions.assertNotNull(user);
    System.out.println(user);
  }

  @Test
  void findUsersFollowedByTest(){
    List<User> usersFollowedBy = repo.findUsersFollowedBy(1);
    Assertions.assertNotNull(usersFollowedBy);
    Assertions.assertTrue(usersFollowedBy.stream().anyMatch(user -> user.getUserId().equals(2)));
  }
}