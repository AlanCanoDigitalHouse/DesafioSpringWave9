package com.api.firstspringchallenge.controllers;

import com.api.firstspringchallenge.dtos.request.PostRequestDTO;
import com.api.firstspringchallenge.dtos.request.PostsRequestDTO;
import com.api.firstspringchallenge.dtos.request.PromoPostRequestDTO;
import com.api.firstspringchallenge.services.post.implementation.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class PostController {
    private final PostService service;

    @PostMapping("/products/newpost")
    public ResponseEntity<Void> newPost(@Valid @RequestBody PostRequestDTO post) {
        return service.newPost(post);
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<Void> getPostsBy(@Valid PostsRequestDTO request) {
        return service.getPostsBy(request);
    }

    @PostMapping("/products/newpromopost")
    public ResponseEntity<Void> newPromoPost(@Valid @RequestBody PromoPostRequestDTO request) {
        return service.newPromoPost(request);
    }

    @GetMapping("/products/{userId}/countPromo")
    public ResponseEntity<Void> promoQuantity(@PathVariable("userId") int userId) {
        return service.getPromoQuantity(userId);
    }

    @GetMapping("/products/{userId}/list")
    public ResponseEntity<Void> promoList(@Valid PostsRequestDTO request) {
        return service.getPromoList(request);
    }

}
