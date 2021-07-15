package com.meli.socialmeli.service.post;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.repository.post.PostRepository;
import com.meli.socialmeli.service.user.UserService;
import com.meli.socialmeli.util.MapperUtils;
import com.meli.socialmeli.util.PostDTOComparatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

  @Autowired
  PostRepository repo;

  @Autowired
  UserService userService;

  @Override
  public void newPost(PostDTO postDTO) throws UserNotFoundException {
    Post post = MapperUtils.dtoToPost(postDTO.getUserId(), postDTO);
    userService.getUser(post.getUserId());
    repo.newPost(post);
  }

  @Override
  public List<PostDTO> getUserPosts(Integer userId) {
    List<Post> userPosts = repo.getUserPosts(userId);
    return MapperUtils.postToDTOList(userPosts);
  }

  @Override
  public List<PostDTO> getPostsOfUsersFollowedBy(Integer userId) throws UserNotFoundException {
    List<UserDTO> usersFollowedBy = userService.getUsersFollowedBy(userId);
    List<PostDTO> result = new ArrayList<>();
    for (UserDTO userDTO : usersFollowedBy) {
      List<PostDTO> userPosts = getUserPosts(userDTO.getUserId());
      result.addAll(userPosts);
    }
    return result;
  }

  @Override
  public List<PostDTO> getPostsOfUsersFollowedBy(Integer userId, String order) throws UserNotFoundException {
    List<PostDTO> postsOfUsersFollowedBy = getPostsOfUsersFollowedBy(userId);
    postsOfUsersFollowedBy.sort(PostDTOComparatorFactory.getComparator(order));
    return postsOfUsersFollowedBy;
  }


  @Override
  public List<PostDTO> getUserPromoPosts(Integer userId) {
    List<Post> userPromoPosts = repo.getUserPromoPosts(userId);
    return MapperUtils.postToDTOList(userPromoPosts);
  }

  @Override
  public Integer getUserPromoPostsCount(Integer userId) {
    List<PostDTO> userPromoPosts = getUserPromoPosts(userId);
    return userPromoPosts.size();
  }
}
