package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.User;
import com.meli.socialmeli.repository.UserRepository;
import com.meli.socialmeli.util.MapperUtils;
import com.meli.socialmeli.util.PostComparatorFactory;
import com.meli.socialmeli.util.UserComparatorFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SocialMeliServiceImp implements SocialMeliService {

  private final Integer DEFAULT_OF_THE_LAST_DAYS = 14;

  private UserRepository repo;

  public SocialMeliServiceImp(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public void addFollower(Integer followerId, Integer followedId) throws UserNotFoundException {
    repo.addFollower(followerId, followedId);
  }

  @Override
  public void removeFollower(Integer followerId, Integer followedId) throws UserNotFoundException {
    repo.removeFollower(followerId, followedId);
  }

  @Override
  public Integer getFollowersCount(Integer userId) throws UserNotFoundException {
    return repo.getFollowersCount(userId);
  }

  @Override
  public User findUser(Integer userId) throws UserNotFoundException {
    return repo.findUser(userId);
  }

  @Override
  public User findUser(Integer userId, String order) throws UserNotFoundException {
    Comparator<User> comparator = getUserComparator(order);
    User user = findUser(userId);
    user.getFolllowers().sort(comparator);
    return user;
  }


  @Override
  public List<User> findUsersFollowedBy(Integer userdId) throws UserNotFoundException {
    return repo.findUsersFollowedBy(userdId);
  }

  @Override
  public List<User> findUsersFollowedBy(Integer userdId, String order) throws UserNotFoundException {
    Comparator<User> userComparator = getUserComparator(order);
    return repo.findUsersFollowedBy(userdId, userComparator);
  }

  @Override
  public void newPost(Integer userId, PostDTO post) throws UserNotFoundException {
    repo.newPost(userId, MapperUtils.dtoToPost(userId, post));
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId) throws UserNotFoundException {
    return repo.findPostsOfSellersFollowedBy(userId, DEFAULT_OF_THE_LAST_DAYS, Post::compareTo);
  }

  @Override
  public List<Post> findUserPromoPosts(Integer userId) throws UserNotFoundException {
    return repo.findUserPromoPosts(userId);
  }

  @Override
  public Integer findUserPromoPostsCount(Integer userId) throws UserNotFoundException {
    List<Post> userPromoPosts = findUserPromoPosts(userId);
    return userPromoPosts.size();
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, String order) throws UserNotFoundException {
    Comparator<Post> comparator = getPostComparator(order);
    return findPostsOfSellersFollowedBy(userId, DEFAULT_OF_THE_LAST_DAYS, comparator);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays) throws UserNotFoundException {
    return repo.findPostsOfSellersFollowedBy(userId, ofTheLastDays);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays, Comparator<Post> c) throws UserNotFoundException {
    return repo.findPostsOfSellersFollowedBy(userId, ofTheLastDays, c);
  }

  private Comparator<Post> getPostComparator(String order) {
    return PostComparatorFactory.getComparator(order);
  }

  private Comparator<User> getUserComparator(String order) {
    return UserComparatorFactory.getComparator(order);
  }

}
