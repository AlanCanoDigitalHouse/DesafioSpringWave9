package com.example.desafiospring.controller;

import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.PromoPostDTO;
import com.example.desafiospring.dtos.request.PostRequestDTO;
import com.example.desafiospring.dtos.request.PromoPostRequestDTO;
import com.example.desafiospring.dtos.response.PostsUserResponseDTO;
import com.example.desafiospring.dtos.response.UserPostsResponseDTO;
import com.example.desafiospring.exceptions.UserNotExistsException;
import com.example.desafiospring.service.IPostService;
import com.example.desafiospring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @PostMapping("/newpost")
    public ResponseEntity<PostDTO> newPost(@Valid @RequestBody PostRequestDTO pPostRequestDTO) throws UserNotExistsException {
        return new ResponseEntity<>(postService.loadPost(pPostRequestDTO), HttpStatus.OK);
    }


    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsUserResponseDTO> getPostsByUser(@Valid @PathVariable int userId) throws UserNotExistsException {
        return new ResponseEntity<>(userService.getPostsByUser(userId, "none"), HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<PromoPostDTO> newPromoPost(@Valid @RequestBody PromoPostRequestDTO pPromoPostRequest) throws UserNotExistsException {
        return new ResponseEntity<>(postService.loadPromoPost(pPromoPostRequest), HttpStatus.OK);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<UserPostsResponseDTO> getPromoPostsByUser(@Valid @PathVariable int userId) throws UserNotExistsException {
        return new ResponseEntity<>(postService.getPromoPosts(userId), HttpStatus.OK);
    }

}
