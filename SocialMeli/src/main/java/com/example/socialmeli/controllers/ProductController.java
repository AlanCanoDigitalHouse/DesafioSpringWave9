package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.requests.PostRequestDto;
import com.example.socialmeli.dtos.requests.PromoPostRequestDto;
import com.example.socialmeli.dtos.responses.CountPromoPostResponseDto;
import com.example.socialmeli.dtos.responses.PostsResponseDto;
import com.example.socialmeli.dtos.responses.PromoPostResponseDto;
import com.example.socialmeli.services.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    IPostService postService;

    public ProductController(IPostService postService) {

        this.postService = postService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Void> newPost(@Valid @RequestBody PostRequestDto post){
        postService.newPost(post);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<Void> newPromoPost(@Valid @RequestBody PromoPostRequestDto post){
        postService.newPromoPost(post);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/followed/{userId}/list", params = {"order"})
    public ResponseEntity<PostsResponseDto> getFollowingPosts(@PathVariable Integer userId, @RequestParam String order){
        return new ResponseEntity<>(postService.getPost(userId,order), HttpStatus.OK);
    }

    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<PostsResponseDto> getFollowingPosts(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getPost(userId,null), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/list", params = {"order"})
    public ResponseEntity<PromoPostResponseDto> getPromoPosts(@PathVariable Integer userId, @RequestParam String order){
        return new ResponseEntity<>(postService.getPromoPost(userId,order), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/list")
    public ResponseEntity<PromoPostResponseDto> getPromoPosts(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getPromoPost(userId,null), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/countPromo", params = {"order"})
    public ResponseEntity<CountPromoPostResponseDto> getCountPromoPosts(@PathVariable Integer userId, @RequestParam String order){
        return new ResponseEntity<>(postService.getCountPromoPost(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/countPromo")
    public ResponseEntity<CountPromoPostResponseDto> getCountPromoPosts(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getCountPromoPost(userId), HttpStatus.OK);
    }


}
