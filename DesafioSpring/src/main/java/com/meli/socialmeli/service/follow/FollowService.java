package com.meli.socialmeli.service.follow;

import java.util.List;

public interface FollowService {
  void addFollower(Integer followerId, Integer followedId);

  void removeFollower(Integer followerId, Integer followedId);

  /**
   * obtiene los usuarios que siguen al usuario especificado
   * @param userId
   * @return los ids de los seguidores
   */
  List<Integer> getFollowers(Integer userId);

  /**
   * obtiene los usuarios que son seguidos por el usuario especificado
   * @param userId
   * @return los ids de los usuarios seguidos
   */
  List<Integer> getFollowedBy(Integer userId);

  Integer getFollowersCount(Integer userId);
}
