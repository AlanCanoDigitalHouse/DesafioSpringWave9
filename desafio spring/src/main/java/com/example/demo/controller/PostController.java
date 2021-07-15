package com.example.demo.controller;

import com.example.demo.dtos.PostDto;
import com.example.demo.dtos.SimplePostDto;
import com.example.demo.dtos.response.PostsListResponseDto;
import com.example.demo.dtos.response.PromoCountResponseDto;
import com.example.demo.dtos.response.PromoPostsFromUserResponseDto;
import com.example.demo.services.interfaces.PostService;
import com.example.demo.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class PostController {

    PostService postService;
    UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Void> createPost(@Valid @RequestBody SimplePostDto simplePostDto) {
        this.postService.createPostFor(simplePostDto , userService.getUser(simplePostDto.getUserId()));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsListResponseDto> listTwoWeeksPosts(@PathVariable int userId, @RequestParam(value = "order", required = false) String order) {
        return new ResponseEntity<>(this.postService.listTwoWeeksPosts(userId , userService.getUsers(), order), HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<Void> createPromoPost(@RequestBody PostDto promoPostDto) {
        this.postService.createPromoPostFor(promoPostDto , userService.getUser(promoPostDto.getUserId()));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<PromoCountResponseDto> countPromoPosts(@PathVariable int userId) {
        return new ResponseEntity<>(this.postService.countPromoPosts(userService.getUser(userId)), HttpStatus.OK);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<PromoPostsFromUserResponseDto> listPostsFrom(@PathVariable int userId) {
        return new ResponseEntity<>(this.postService.listPromoPosts(userService.getUser(userId)), HttpStatus.OK);
    }
}


