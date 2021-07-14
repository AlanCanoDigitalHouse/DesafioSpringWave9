package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.PostsOfSellersFollowedByDTO;
import com.meli.socialmeli.dto.UserPromoPostCountDTO;
import com.meli.socialmeli.dto.UserPromoPostsDTO;
import com.meli.socialmeli.dto.request.NewPostRequest;
import com.meli.socialmeli.model.Post;
import com.meli.socialmeli.model.User;
import com.meli.socialmeli.service.SocialMeliService;
import com.meli.socialmeli.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class PostController {

  @Autowired
  SocialMeliService service;

  @PostMapping("newpost")
  public ResponseEntity<String> newPost(@RequestBody NewPostRequest newPostRequest) {
    service.newPost(newPostRequest.getUserId(), MapperUtils.buildDTOFromRequest(newPostRequest));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("followed/{userId}/list")
  public ResponseEntity<PostsOfSellersFollowedByDTO> findPostsOfSellersFollowedBy(@PathVariable Integer userId,
                                                                                  @RequestParam(required = false) String order) {
    List<Post> postsOfSellersFollowedBy = service.findPostsOfSellersFollowedBy(userId, order);
    PostsOfSellersFollowedByDTO postsOfSellersFollowedByDTO = new PostsOfSellersFollowedByDTO(userId,
            MapperUtils.postDTOList(postsOfSellersFollowedBy));
    return new ResponseEntity<>(postsOfSellersFollowedByDTO, HttpStatus.OK);
  }

  @PostMapping("newpromopost")
  public ResponseEntity<String> newPromoPost(@RequestBody NewPostRequest newPostRequest) {
    service.newPost(newPostRequest.getUserId(), MapperUtils.buildDTOFromRequest(newPostRequest));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("{userId}/countPromo")
  public ResponseEntity<UserPromoPostCountDTO> getUserPromoPostCount(@PathVariable Integer userId) {
    Integer userPromoPostsCount = service.findUserPromoPostsCount(userId);
    User user = service.findUser(userId);
    UserPromoPostCountDTO userPromoPostCountDTO = new UserPromoPostCountDTO(userId, user.getUserName(), userPromoPostsCount);
    return new ResponseEntity<>(userPromoPostCountDTO, HttpStatus.OK);
  }

  @GetMapping("{userId}/list")
  public ResponseEntity<UserPromoPostsDTO> getUserPromoPosts(@PathVariable Integer userId) {
    List<Post> userPromoPosts = service.findUserPromoPosts(userId);
    User user = service.findUser(userId);
    UserPromoPostsDTO userPromoPostsDTO = new UserPromoPostsDTO(userId, user.getUserName(), userPromoPosts);
    return new ResponseEntity<>(userPromoPostsDTO, HttpStatus.OK);
  }

}
