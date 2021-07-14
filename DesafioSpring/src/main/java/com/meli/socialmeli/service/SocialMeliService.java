package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.User;

import java.util.Comparator;
import java.util.List;

public interface SocialMeliService {
  public void addFollower(Integer followerId, Integer followedId);

  public void removeFollower(Integer followerId, Integer followedId);


  public Integer getFollowersCount(Integer userId);

  public User findUser(Integer userId);

  public User findUser(Integer userId, String order);

  public List<User> findUsersFollowedBy(Integer userdId);

  public List<User> findUsersFollowedBy(Integer userdId, String order);


  public void newPost(Integer userId, PostDTO post);

  public List<Post> findPostsOfSellersFollowedBy(Integer userId);

  public List<Post> findUserPromoPosts(Integer userId);

  public Integer findUserPromoPostsCount(Integer userId);

  public List<Post> findPostsOfSellersFollowedBy(Integer userId, String order);

  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays);

  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays, Comparator<Post> c);

}
