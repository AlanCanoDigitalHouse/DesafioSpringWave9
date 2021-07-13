package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.PostsOfSellersFollowedByDTO;
import com.meli.socialmeli.dto.request.NewPostRequest;
import com.meli.socialmeli.model.Post;
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

//  @GetMapping("followed/{userId}/list")
//  public ResponseEntity<PostsOfSellersFollowedByDTO> findPostsOfSellersFollowedBy(@PathVariable Integer userId) {
//    List<Post> postsOfSellersFollowedBy = service.findPostsOfSellersFollowedBy(userId);
//    PostsOfSellersFollowedByDTO postsOfSellersFollowedByDTO = new PostsOfSellersFollowedByDTO(userId,
//            MapperUtils.postDTOList(postsOfSellersFollowedBy));
//    return new ResponseEntity<>(postsOfSellersFollowedByDTO, HttpStatus.OK);
//  }

  @GetMapping("followed/{userId}/list")
  public ResponseEntity<PostsOfSellersFollowedByDTO> findPostsOfSellersFollowedBy(@PathVariable Integer userId,
                                                                                  @RequestParam(required = false) String order) {
    List<Post> postsOfSellersFollowedBy = null;
    if (order != null) {
      postsOfSellersFollowedBy = service.findPostsOfSellersFollowedBy(userId, order);

    } else {
      postsOfSellersFollowedBy = service.findPostsOfSellersFollowedBy(userId);
    }
    PostsOfSellersFollowedByDTO postsOfSellersFollowedByDTO = new PostsOfSellersFollowedByDTO(userId,
            MapperUtils.postDTOList(postsOfSellersFollowedBy));
    return new ResponseEntity<>(postsOfSellersFollowedByDTO, HttpStatus.OK);
  }


}
