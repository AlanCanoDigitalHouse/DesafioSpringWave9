package com.meli.socialmeli.repository.post;

import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.Product;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImp implements PostRepository {
  private final Map<Integer, Post> posts;

  public PostRepositoryImp(Map<Integer, Post> posts) {
    this.posts = posts;
  }

  public PostRepositoryImp() {
    this.posts = loadPosts();
  }

  @Override
  public void newPost(Post post) {
    posts.put(post.getPostId(), post);
  }

  @Override
  public List<Post> getUserPosts(Integer userId) {
    return posts.values().stream().filter(post -> post.getUserId().equals(userId)).collect(Collectors.toList());
  }

  @Override
  public List<Post> getUserPromoPosts(Integer userId) {
    List<Post> userPosts = getUserPosts(userId);
    return userPosts.stream().filter(Post::getHasPromo).collect(Collectors.toList());
  }

  @Override
  public Integer getUserPromoPostsCount(Integer userId) {
    List<Post> userPromoPosts = getUserPromoPosts(userId);
    return userPromoPosts.size();
  }

  private Map<Integer, Post> loadPosts() {
    Map<Integer, Post> map = new HashMap<>();
    Product sillaRoja = Product.builder()
            .productId(1)
            .productName("Silla Gamer")
            .type("Gamer")
            .brand("Racer")
            .color("Red & Balck")
            .notes("Special Edition")
            .build();

    Post recentPost = Post.builder()
            .userId(2)
            .postId(18)
            .date(LocalDate.now())
            .detail(sillaRoja)
            .category(100)
            .price(1500.05)
            .hasPromo(false)
            .discount(0.0)
            .build();

    Product mouseAzul = Product.builder()
            .productId(19)
            .productName("Mouse Gamer")
            .type("Gamer")
            .brand("Racer")
            .color("Azul")
            .notes("Standar")
            .build();

    Post oldPost = Post.builder()
            .userId(2)
            .postId(3)
            .date(LocalDate.now().minusDays(3))
            .detail(mouseAzul)
            .category(100)
            .price(1300.05)
            .hasPromo(true)
            .discount(0.15)
            .build();

    Product teclado = Product.builder()
            .productId(19)
            .productName("Teclado Gamer")
            .type("Gamer")
            .brand("Racer")
            .color("RGB")
            .notes("Standar")
            .build();

    Post reallyOldPost = Post.builder()
            .userId(2)
            .postId(3)
            .date(LocalDate.now().minusMonths(2))
            .detail(teclado)
            .category(100)
            .price(1300.05)
            .hasPromo(true)
            .discount(0.15)
            .build();

    map.put(recentPost.getPostId(), recentPost);
    map.put(oldPost.getPostId(), oldPost);
    map.put(reallyOldPost.getPostId(), reallyOldPost);
    return map;
  }
}
