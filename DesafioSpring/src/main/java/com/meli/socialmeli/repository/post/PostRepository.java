package com.meli.socialmeli.repository.post;

import com.meli.socialmeli.model.Post;

import java.util.List;

public interface PostRepository {
  void newPost(Post post);

  List<Post> getUserPosts(Integer userId);

  List<Post> getUserPromoPosts(Integer userId);

  Integer getUserPromoPostsCount(Integer userId);
}
