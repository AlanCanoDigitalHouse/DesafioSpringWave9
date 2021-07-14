package com.meli.socialmeli.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class User implements Comparable<User> {

  private Integer userId;
  private String userName;
  private List<User> folllowers;
  private List<Post> posts;

  public User(Integer userId, String userName, List<User> folllowers) {
    this.userId = userId;
    this.userName = userName;
    this.folllowers = folllowers;
    this.posts = new ArrayList<>();
  }

  public User(Integer userId, String userName) {
    this.userId = userId;
    this.userName = userName;
    this.folllowers = new ArrayList<>();
    this.posts = new ArrayList<>();
  }

  public User(Integer userId, String userName, List<User> folllowers, List<Post> posts) {
    this.userId = userId;
    this.userName = userName;
    this.folllowers = folllowers;
    this.posts = posts;
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

  @Override
  public int compareTo(User o) {
    return this.getUserName().compareTo(o.userName);
  }
}
