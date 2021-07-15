package com.jbianchini.meli.socialmeli.controller;

import com.jbianchini.meli.socialmeli.dto.PostDTO;
import com.jbianchini.meli.socialmeli.dto.PostsByFollowerDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;
import com.jbianchini.meli.socialmeli.service.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    IPostService productService;

    public ProductController(IPostService productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public ResponseDTO newPost(@RequestBody @Valid PostDTO postDTO) {
        return this.productService.newPost(postDTO);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsByFollowerDTO> followedPosts(@PathVariable Integer userId,
                                                            @RequestParam(defaultValue = "") String order) {
        return new ResponseEntity<>(this.productService.getFollowedPostsByUserId(userId, order), HttpStatus.OK);
    }


}
