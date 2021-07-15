package com.meli.socialmeli.repository.user;

import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepositoryImp implements UserRepository {

  private final List<User> users;

  public UserRepositoryImp(List<User> users) {
    this.users = users;
  }

  public UserRepositoryImp() {
    this.users = loadUsers();
  }

  @Override
  public User getUser(Integer userId) throws UserNotFoundException {
    try {
      return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().get();
    } catch (NoSuchElementException exception) {
      throw new UserNotFoundException("No existe el usuario con id " + userId);
    }
  }

  private List<User> loadUsers() {
    User emilio = new User(1, "emilio");
    User daniel = new User(2, "daniel");
    User ofe = new User(3, "ofe");
    ArrayList<User> users = new ArrayList<>();
    users.add(emilio);
    users.add(daniel);
    users.add(ofe);
    users.add(new User(4, "elizabeth"));
    return users;
  }
}
