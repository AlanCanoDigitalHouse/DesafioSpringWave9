package com.desafiospring.socialmeli.controllers;

import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.services.IProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProduct productService;

    public ProductController(IProduct productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> newPost(@Valid @RequestBody Post post) throws UserException {
        Post postResponse = productService.addPost(post);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }
}
