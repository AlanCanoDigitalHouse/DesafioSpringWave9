package com.desafio.spring.controllers;

import com.desafio.spring.dtos.PostDto;
import com.desafio.spring.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController
{
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //05
    @PostMapping("products/newpost")
    public ResponseEntity newPost(@RequestBody PostDto post){
        postService.newPost(post);
        return new ResponseEntity(HttpStatus.OK);
    }

    //06
    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<PostDto> allPosts(@PathVariable Integer userId, @RequestParam(defaultValue = "date_desc") String date){
        List<PostDto> postOfSeller = postService.getPostSeller(userId, date);
        return new ResponseEntity<>((PostDto) postOfSeller, HttpStatus.OK);
    }

}
