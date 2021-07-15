package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.*;
import com.meli.socialmeli.dto.request.NewPostRequest;
import com.meli.socialmeli.dto.request.NewPromoPostRequest;
import com.meli.socialmeli.exception.UserNotFoundException;
import com.meli.socialmeli.service.post.PostService;
import com.meli.socialmeli.service.user.UserService;
import com.meli.socialmeli.util.MapperUtils;
import com.meli.socialmeli.util.constants.SocialMeliConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("products")
@Validated
public class PostController {

  @Autowired
  PostService postService;

  @Autowired
  UserService userService;

  @PostMapping("newpost")
  public ResponseEntity<String> newPost(@Valid @RequestBody NewPostRequest newPostRequest) throws UserNotFoundException {
    postService.newPost(MapperUtils.buildDTOFromRequest(newPostRequest));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("followed/{userId}/list")
  public ResponseEntity<PostsOfSellersFollowedByDTO> findPostsOfSellersFollowedBy(@PathVariable @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer userId, @RequestParam(required = false) @Pattern(regexp = SocialMeliConstants.ORDER_DATE_PATTERN, message = SocialMeliConstants.VALIDATION_ORDER_PATTERN_MESSAGE) String order) throws UserNotFoundException {
    List<PostDTO> postsOfSellersFollowedBy = postService.getPostsOfUsersFollowedBy(userId, order);
    PostsOfSellersFollowedByDTO postsOfSellersFollowedByDTO = new PostsOfSellersFollowedByDTO(userId,
            postsOfSellersFollowedBy);
    return new ResponseEntity<>(postsOfSellersFollowedByDTO, HttpStatus.OK);
  }

  @PostMapping("newpromopost")
  public ResponseEntity<String> newPromoPost(@Valid @RequestBody NewPromoPostRequest newPostRequest) throws UserNotFoundException {
    postService.newPost(MapperUtils.buildDTOFromRequest(newPostRequest));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("{userId}/countPromo")
  public ResponseEntity<UserPromoPostCountDTO> getUserPromoPostCount(@PathVariable @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer userId) throws UserNotFoundException {
    Integer userPromoPostsCount = postService.getUserPromoPostsCount(userId);
    UserDTO user = userService.getUser(userId);
    UserPromoPostCountDTO userPromoPostCountDTO = new UserPromoPostCountDTO(userId, user.getUserName(), userPromoPostsCount);
    return new ResponseEntity<>(userPromoPostCountDTO, HttpStatus.OK);
  }

  @GetMapping("{userId}/list")
  public ResponseEntity<UserPromoPostsDTO> getUserPromoPosts(@PathVariable @Positive(message = SocialMeliConstants.VALIDATION_POSITIVE_MESSAGE) Integer userId) throws UserNotFoundException {
    List<PostDTO> userPromoPosts = postService.getUserPromoPosts(userId);
    UserDTO user = userService.getUser(userId);
    UserPromoPostsDTO userPromoPostsDTO = new UserPromoPostsDTO(userId, user.getUserName(), userPromoPosts);
    return new ResponseEntity<>(userPromoPostsDTO, HttpStatus.OK);
  }

}
