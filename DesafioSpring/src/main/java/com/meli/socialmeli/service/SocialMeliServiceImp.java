package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.User;
import com.meli.socialmeli.repository.UserRepository;
import com.meli.socialmeli.util.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SocialMeliServiceImp implements SocialMeliService {

  private Integer DEFAULT_OF_THE_LAST_DAYS = 14;
  private String ALPHA_ASCENDING = "name_asc";
  private String ALPHA_DESCENDING = "name_desc";
  private String DATE_ASCENDING = "date_asc";
  private String DATE_DESCENDING = "date_desc";

  private UserRepository repo;

  public SocialMeliServiceImp(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public void addFollower(Integer followerId, Integer followedId) {
    repo.addFollower(followerId, followedId);
  }

  @Override
  public void removeFollower(Integer followerId, Integer followedId) {
    repo.removeFollower(followerId, followedId);
  }

  @Override
  public Integer getFollowersCount(Integer userId) {
    return repo.getFollowersCount(userId);
  }

  @Override
  public User findUser(Integer userId) {
    return repo.findUser(userId);
  }

  @Override
  public List<User> findUsersFollowedBy(Integer userdId) {
    return repo.findUsersFollowedBy(userdId);
  }

  @Override
  public void newPost(Integer userId, PostDTO post) {
    repo.newPost(userId, MapperUtils.dtoToPost(userId, post));
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId) {
    return repo.findPostsOfSellersFollowedBy(userId, DEFAULT_OF_THE_LAST_DAYS, Post::compareTo);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, String order) {
    Comparator<Post> comparator = getComparator(order);
    return findPostsOfSellersFollowedBy(userId, DEFAULT_OF_THE_LAST_DAYS, comparator);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays) {
    return repo.findPostsOfSellersFollowedBy(userId, ofTheLastDays);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays, Comparator<Post> c) {
    return repo.findPostsOfSellersFollowedBy(userId, ofTheLastDays, c);
  }

  private Comparator<Post> getComparator(String order) {
    if (order.equals(ALPHA_ASCENDING)) {
      return new Comparator<Post>() {
        @Override
        public int compare(Post o1, Post o2) {
          return o1.getDetail().getProductName().compareTo(o2.getDetail().getProductName());
        }
      };
    } else if (order.equals(ALPHA_DESCENDING)) {
      return new Comparator<Post>() {
        @Override
        public int compare(Post o1, Post o2) {
          int i = o1.getDetail().getProductName().compareTo(o2.getDetail().getProductName());
          return -1 * i;
        }
      };
    } else if (order.equals(DATE_ASCENDING)) {
      return new Comparator<Post>() {
        @Override
        public int compare(Post o1, Post o2) {
          return o1.getDate().isBefore(o2.getDate()) ? -1 : o1.getDate().isAfter(o2.getDate()) ? 1 : 0;
        }
      };
    } else if (order.equals(DATE_DESCENDING)) {
      return new Comparator<Post>() {
        @Override
        public int compare(Post o1, Post o2) {
          return o1.getDate().isBefore(o2.getDate()) ? 1 : o1.getDate().isAfter(o2.getDate()) ? -1 : 0;
        }
      };
    } else {
      return Post::compareTo;
    }
  }
}
