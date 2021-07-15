package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.Post;
import com.example.desafiospring.dtos.UserPosts;
import com.example.desafiospring.exceptions.*;
import com.example.desafiospring.services.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@RestController
@RequestMapping("/products")
@Validated
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("/newpost")
    public ResponseEntity createPost(@Valid @RequestBody Post post) throws UserNotExistException, NeedDiscountException {
        postService.createPost(post);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/followed/{userId}/list")
    public UserPosts getFollowedPosts(
            @Valid @PathVariable @Min(message = "need positive number, min required 0", value=0)  Integer userId,
            @RequestParam(value = "order", defaultValue = "none", required = false) String order)
            throws UserNotExistException, UserDontHavePostsException, UserDontHaveFollowersorFollowed {
        return postService.findPostsByUserId(userId, order);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity createPromoPost(@Valid @RequestBody Post post)
            throws UserNotExistException, NeedDiscountException, HasPromoException, DontNeedDiscountException {
            postService.createPromoPost(post);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{userId}/countPromo")
    public UserPosts countPromo(@Valid @PathVariable Integer userId) throws UserNotExistException, UserDontHavePostsException {
        return postService.countPromo(userId);
    }

    @GetMapping("{userId}/list")
    public UserPosts getPostsById(
            @Valid @PathVariable @Min(message = "need positive number, min required 0", value=0) Integer userId)
            throws UserDontHavePostsException, UserNotExistException, NotHavePromoProducts {
        return postService.getPosts(userId);
    }


}
