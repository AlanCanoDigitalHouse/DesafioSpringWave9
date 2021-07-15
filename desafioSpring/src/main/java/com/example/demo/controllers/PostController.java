package com.example.demo.controllers;

import com.example.demo.dtos.request.Post;
import com.example.demo.dtos.response.PostList;
import com.example.demo.exceptions.EmptyFile;
import com.example.demo.exceptions.InvalidDate;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.text.ParseException;

@RestController
@RequestMapping("/products")
@Validated
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) throws FileNotFoundException, InvalidDate {
        postService.createPost(post);
        return new ResponseEntity("Post saved!", HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity getPostByUser(@PathVariable Integer userId,
                                        @RequestParam(value = "order", defaultValue = "") String order)
            throws UserNotFound, EmptyFile {
        PostList response = new PostList();
        if(!order.isBlank()){
            response = postService.orderPost(order, userId);
        } else {
            response = postService.getPostList(userId);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
