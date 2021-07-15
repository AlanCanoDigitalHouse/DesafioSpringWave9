package com.example.demo.Controllers;

import com.example.demo.DTOs.FollowedPostsDTO;
import com.example.demo.DTOs.PostDTO;
import com.example.demo.DTOs.PostsDTO;
import com.example.demo.Exceptions.ExceptionHandler;
import com.example.demo.Services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PostController {

    @Autowired
    IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<String> createPost(@RequestBody PostDTO post) throws ExceptionHandler {
        postService.createPost(post);
        return new ResponseEntity("New post created", HttpStatus.OK);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<PostsDTO> getPostsByUserId(@PathVariable int userId) throws ExceptionHandler {
        return new ResponseEntity(postService.getPostsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedPostsDTO> getFollowedPosts(@PathVariable int userId, @RequestParam(defaultValue = "date_desc") String order, @RequestParam(defaultValue = "14") int daysBefore) throws ExceptionHandler {
        return new ResponseEntity(postService.getFollowedPosts(userId, order, daysBefore),HttpStatus.OK);
    }

}
