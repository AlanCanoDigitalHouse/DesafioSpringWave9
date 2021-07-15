package com.meli.socialmeli.service.post;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.exception.UserNotFoundException;

import java.util.List;

public interface PostService {
  void newPost(PostDTO post) throws UserNotFoundException;

  List<PostDTO> getUserPosts(Integer userId);

  List<PostDTO> getPostsOfUsersFollowedBy(Integer userId) throws UserNotFoundException;

  List<PostDTO> getPostsOfUsersFollowedBy(Integer userId, String order) throws UserNotFoundException;

  List<PostDTO> getUserPromoPosts(Integer userId);

  Integer getUserPromoPostsCount(Integer userId);
}
