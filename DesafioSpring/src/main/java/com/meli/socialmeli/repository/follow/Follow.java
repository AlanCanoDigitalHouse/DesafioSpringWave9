package com.meli.socialmeli.repository.follow;

import lombok.Data;

import java.util.Objects;

@Data
public class Follow {
  private Integer followerKey;
  private Integer followedKey;

  public Follow(Integer followerKey, Integer followedKey) {
    this.followerKey = followerKey;
    this.followedKey = followedKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Follow)) return false;
    Follow follow = (Follow) o;
    return Objects.equals(followerKey, follow.followerKey) && Objects.equals(followedKey, follow.followedKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(followerKey, followedKey);
  }
}
