package com.meli.socialmeli.repository.post;

import com.meli.socialmeli.model.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryImpTest {
  PostRepository repo;

  @BeforeEach
  void setUp() {
    repo = new PostRepositoryImp();
  }

  @Test
  void newPost() {
  }

  @Test
  void getUserPosts() {
  }

  @Test
  void getUserPromoPosts() {
    List<Post> userPromoPosts = repo.getUserPromoPosts(10);
    Assertions.assertTrue(userPromoPosts.isEmpty());
  }

  @Test
  void getUserPromoPostsCount() {
    Integer userPromoPostsCount = repo.getUserPromoPostsCount(2);
    Assertions.assertTrue(!userPromoPostsCount.equals(0));
  }
}