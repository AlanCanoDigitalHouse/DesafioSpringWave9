package com.example.desafiospringboot.controller;

import com.example.desafiospringboot.dto.UserPost;
import com.example.desafiospringboot.model.Post;
import com.example.desafiospringboot.service.PostService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/products/newpost")
    public void postProduct(@RequestBody JSONObject body, HttpServletResponse response){
        Post p = new Post();
        p.asimilar(body);
        postService.newPost(p);

    }
    @GetMapping("/products/followed/{userId}/list")
    public UserPost userPosts(@PathVariable(value = "userId") int userId){
        return postService.postList(userId);
    }
}
