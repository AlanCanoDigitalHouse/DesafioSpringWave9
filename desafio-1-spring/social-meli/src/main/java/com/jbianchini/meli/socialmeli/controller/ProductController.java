package com.jbianchini.meli.socialmeli.controller;

import com.jbianchini.meli.socialmeli.dto.request.NewPostRequest;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.model.Post;
import com.jbianchini.meli.socialmeli.service.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    IPostService productService;

    public ProductController(IPostService productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> newPost(@RequestBody NewPostRequest newPostRequest) throws ApplicationException {
        return new ResponseEntity<>(this.productService.newPost(newPostRequest), HttpStatus.OK);
    }
}
