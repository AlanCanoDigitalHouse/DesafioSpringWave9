package com.example.desafio1.controllers;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;
import com.example.desafio1.services.IProductService;
import com.example.desafio1.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;
    private final IUserService userService;

    public ProductController(IProductService productService, IUserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/newpost")
    public ResponseEntity createNewPost(@RequestBody Post post){
        productService.addNewPost(post);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public List<Post> listFollowedPosts(@PathVariable Integer userId){
        List<User> vendors = userService.findFollowed(userId);
        return productService.listLastFollowedPosts(vendors);
    }

}
