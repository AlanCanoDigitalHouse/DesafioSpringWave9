package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.User;

import java.util.Comparator;
import java.util.List;

public interface SocialMeliService {
  void addFollower(Integer followerId, Integer followedId) throws UserNotFoundException;

  void removeFollower(Integer followerId, Integer followedId) throws UserNotFoundException;


  Integer getFollowersCount(Integer userId) throws UserNotFoundException;

  User findUser(Integer userId) throws UserNotFoundException;

  User findUser(Integer userId, String order) throws UserNotFoundException;

  List<User> findUsersFollowedBy(Integer userdId) throws UserNotFoundException;

  List<User> findUsersFollowedBy(Integer userdId, String order) throws UserNotFoundException;


  void newPost(Integer userId, PostDTO post) throws UserNotFoundException;

  List<Post> findPostsOfSellersFollowedBy(Integer userId) throws UserNotFoundException;

  List<Post> findUserPromoPosts(Integer userId) throws UserNotFoundException;

  Integer findUserPromoPostsCount(Integer userId) throws UserNotFoundException;

  List<Post> findPostsOfSellersFollowedBy(Integer userId, String order) throws UserNotFoundException;

  List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays) throws UserNotFoundException;

  List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays, Comparator<Post> c) throws UserNotFoundException;

}
