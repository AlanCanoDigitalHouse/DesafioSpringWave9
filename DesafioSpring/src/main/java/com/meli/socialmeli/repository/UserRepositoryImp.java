package com.meli.socialmeli.repository;

import com.meli.socialmeli.exception.UserNotFoundException;
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
  public void addFollower(Integer followerId, Integer followedId) throws UserNotFoundException {
    User follower = findUserById(followerId);
    User followed = findUserById(followedId);
    List<User> folllowers = followed.getFolllowers();
    if (!folllowers.contains(new User(followerId, null)) && !followedId.equals(followerId)) {
      followed.getFolllowers().add(follower);
    }
  }

  @Override
  public void removeFollower(Integer followerId, Integer followedId) throws UserNotFoundException {
    User follower = findUserById(followerId);
    User followed = findUserById(followedId);
    followed.getFolllowers().remove(new User(follower.getUserId(), null));
  }

  @Override
  public Integer getFollowersCount(Integer userId) throws UserNotFoundException {
    User user = findUserById(userId);
    return user.getFolllowers().size();
  }

  @Override
  public User findUser(Integer userId) throws UserNotFoundException {
    return findUserById(userId);
  }

  @Override
  public List<Post> findUserPromoPosts(Integer userId) throws UserNotFoundException {
    User user = findUserById(userId);
    return user.getPosts().stream().filter(Post::getHasPromo).collect(Collectors.toList());
  }

  @Override
  public Integer findUserPromoPostsCount(Integer userId) throws UserNotFoundException {
    List<Post> userPromoPosts = findUserPromoPosts(userId);
    return userPromoPosts.size();
  }

  @Override
  public List<User> findUsersFollowedBy(Integer userdId) throws UserNotFoundException {
    User userById = findUserById(userdId);
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
  public List<User> findUsersFollowedBy(Integer userdId, Comparator<User> c) throws UserNotFoundException {
    List<User> usersFollowedBy = findUsersFollowedBy(userdId);
    usersFollowedBy.sort(c);
    return usersFollowedBy;
  }

  @Override
  public void newPost(Integer userId, Post post) throws UserNotFoundException {
    User user = findUserById(userId);
    user.getPosts().add(post);
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId) throws UserNotFoundException {
    List<User> usersFollowedBy = findUsersFollowedBy(userId);
    ArrayList<Post> posts = new ArrayList<>();
    usersFollowedBy.forEach(seller -> posts.addAll(seller.getPosts()));
    return posts;
  }


  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays) throws UserNotFoundException {
    List<Post> postsOfSellersFollowedBy = findPostsOfSellersFollowedBy(userId);
    LocalDate dateOfTheLastDays = LocalDate.now().minusDays(ofTheLastDays);
    return postsOfSellersFollowedBy.stream().filter(post -> post.getDate().isAfter(dateOfTheLastDays)).collect(Collectors.toList());
  }

  @Override
  public List<Post> findPostsOfSellersFollowedBy(Integer userId, Integer ofTheLastDays, Comparator<Post> c) throws UserNotFoundException {
    List<Post> postsOfSellersFollowedBy = findPostsOfSellersFollowedBy(userId, ofTheLastDays);
    postsOfSellersFollowedBy.sort(c);
    return postsOfSellersFollowedBy;
  }

  private User findUserById(Integer userId) throws UserNotFoundException {
    try {
      return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().get();
    } catch (NoSuchElementException exception) {
      throw new UserNotFoundException("No existe el usuario con id " + userId);
    }

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
            .hasPromo(false)
            .discount(0.0)
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
            .hasPromo(true)
            .discount(0.15)
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
