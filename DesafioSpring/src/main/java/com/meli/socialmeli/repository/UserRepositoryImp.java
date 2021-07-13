package com.meli.socialmeli.repository;

import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.Product;
import com.meli.socialmeli.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImp implements UserRepository {

  private List<User> users;

  public UserRepositoryImp(List<User> users) {
    this.users = users;
  }

  public UserRepositoryImp() {
    this.users = loadUsers();
  }

  @Override
  public void addFollower(Integer followerId, Integer followedId) {
    Optional<User> optionalFollower = findUserById(followerId);
    Optional<User> optionalFollowed = findUserById(followedId);
    if (optionalFollower.isPresent() && optionalFollowed.isPresent()) {
      User followed = optionalFollowed.get();
      User follower = optionalFollower.get();
      followed.getFolllowers().add(follower);
    }
  }

  @Override
  public void removeFollower(Integer followerId, Integer followedId) {
    Optional<User> optionalFollower = findUserById(followerId);
    Optional<User> optionalFollowed = findUserById(followedId);
    if (optionalFollower.isPresent() && optionalFollowed.isPresent()) {
      User followed = optionalFollowed.get();
      User follower = optionalFollower.get();
      followed.getFolllowers().remove(new User(follower.getUserId(), null));
    }

  }

  @Override
  public Integer getFollowersCount(Integer userId) {
    Optional<User> user = findUserById(userId);
    return user.isPresent() ? user.get().getFolllowers().size() : 0;
  }

  @Override
  public User findUser(Integer userId) {
    Optional<User> userById = findUserById(userId);
    return userById.orElse(null);
  }

  @Override
  public List<User> findUsersFollowedBy(Integer userdId) {
    ArrayList<User> sellersFollowedByUser = new ArrayList<>();
    for (User seller : users) {
      for (User follower : seller.getFolllowers()) {
        if (follower.getUserId().equals(userdId)) {
          sellersFollowedByUser.add(seller);
        }
      }
    }
    return sellersFollowedByUser;
  }

  @Override
  public List<User> findUsersFollowedBy(Integer userdId, Comparator<User> c) {
    List<User> usersFollowedBy = findUsersFollowedBy(userdId);
    Collections.sort(usersFollowedBy, c);
    return usersFollowedBy;
  }

  @Override
  public void newPost(Integer userId, Post post) {
    Optional<User> optionalUser = findUserById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.getPosts().add(post);
    }
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId) {
    List<User> usersFollowedBy = findUsersFollowedBy(userId);
    ArrayList<Post> posts = new ArrayList<>();
    usersFollowedBy.forEach(seller -> seller.getPosts().forEach(posts::add));
    return posts;
  }


  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays) {
    List<Post> postsOfSellersFollowedBy = findPostsOfSellersFollowedBy(userId);
    LocalDate dateOfTheLastDays = LocalDate.now().minusDays(ofTheLastDays);
    return postsOfSellersFollowedBy.stream().filter(post -> post.getDate().isAfter(dateOfTheLastDays)).collect(Collectors.toList());
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays, Comparator<Post> c) {
    List<Post> postsOfSellersFollowedBy = findPostsOfSellersFollowedBy(userId, ofTheLastDays);
    Collections.sort(postsOfSellersFollowedBy, c);
    return postsOfSellersFollowedBy;
  }

  private Optional<User> findUserById(Integer userId) {
    return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
  }

  private List<User> loadUsers() {
    ArrayList<User> users = new ArrayList<>();
    User emilio = new User(1, "emilio");
    User daniel = new User(2, "daniel");
    User ofe = new User(3, "ofe");
    daniel.getFolllowers().add(emilio);
    daniel.getFolllowers().add(ofe);
    ofe.getFolllowers().add(emilio);

    Product sillaRoja = Product.builder()
            .productName("Silla Gamer")
            .type("Gamer")
            .brand("Racer")
            .color("Red & Balck")
            .notes("Special Edition")
            .build();

    Post recentPost = Post.builder()
            .userId(daniel.getUserId())
            .date(LocalDate.now())
            .detail(sillaRoja)
            .category(100)
            .price(1500.05)
            .build();

    Product sillaAzul = Product.builder()
            .productName("Mouse Gamer")
            .type("Gamer")
            .brand("Racer")
            .color("Azul")
            .notes("Standar")
            .build();

    Post oldPost = Post.builder()
            .userId(daniel.getUserId())
            .date(LocalDate.now().minusDays(3))
            .detail(sillaAzul)
            .category(100)
            .price(1300.05)
            .build();

    daniel.getPosts().add(recentPost);
    daniel.getPosts().add(oldPost);

    users.add(emilio);
    users.add(daniel);
    users.add(ofe);
    users.add(new User(4, "elizabeth"));
    return users;
  }
}
