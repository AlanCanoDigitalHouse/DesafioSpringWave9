package com.meli.socialmeli.model;

import lombok.Data;

import java.util.*;

@Data
public class User {

  private Integer userId;
  private String userName;
  private Set<User> folllowers;

  public User(Integer userId, String userName, Set<User> folllowers) {
    this.userId = userId;
    this.userName = userName;
    this.folllowers = folllowers;
  }

  public User(Integer userId, String userName) {
    this.userId = userId;
    this.userName = userName;
    this.folllowers = new HashSet<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(userId, user.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }
}
