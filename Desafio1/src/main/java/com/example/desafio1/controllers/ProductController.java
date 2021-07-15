package com.example.desafio1.controllers;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;
import com.example.desafio1.dto.response.ListFollowedPostsResponseDTO;
import com.example.desafio1.services.IProductService;
import com.example.desafio1.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Void> createNewPost(@Valid @RequestBody Post post){
        productService.addNewPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ListFollowedPostsResponseDTO listFollowedPosts(@PathVariable Integer userId,
                                                          @RequestParam(required = false) String order){
        List<User> vendors = userService.findFollowed(userId, order);
        return new ListFollowedPostsResponseDTO(
                userId,
                productService.listLastFollowedPosts(vendors, order));
    }

}
