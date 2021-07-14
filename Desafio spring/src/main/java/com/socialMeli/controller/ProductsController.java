package com.socialMeli.controller;

import com.socialMeli.SocialMeliApplication;
import com.socialMeli.dto.request.product.PostInfoToCreateDTO;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);
    private final PostService postService;

    public ProductsController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Void> newPost(@Valid @RequestBody PostInfoToCreateDTO postInfo) throws ModelAlreadyExists, ParseException, ModelNotExists {
        postService.addNewPost(postInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
