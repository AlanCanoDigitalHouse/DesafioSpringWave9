package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseDTO;
import com.mercadolibre.socialmeli.service.PostService;
import com.mercadolibre.socialmeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Validated
@RestController
public class PostController {

    PostService postService;
    UserService userService;

    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/products/newpost")
    @ResponseStatus(HttpStatus.CREATED)
    public String newPost(
            @Valid @RequestBody PostRequestDTO postRequestDTO
            ){
        int postId = postService.newPost(postRequestDTO);
        userService.addPostToUser(postRequestDTO.getUserId(), postId);
        return "post successfully created";
    }

    @GetMapping("/products/followed/{userId}/list")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PostResponseDTO getPostsFollowed(@PathVariable int userId){
        return postService.getPostsFollowed(userId);
    }
}
