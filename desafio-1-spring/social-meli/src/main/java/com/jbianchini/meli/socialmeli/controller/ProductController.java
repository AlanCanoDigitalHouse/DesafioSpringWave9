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

    /**
     * Exercise 5: Creates a new post
     *
     * @param postDTO new post.
     * @return ResponseDTO with the response status and the argument data
     */
    @PostMapping("/newpost")
    public ResponseDTO newPost(@RequestBody @Valid PostDTO postDTO) {
        return this.productService.newPost(postDTO);
    }

    /**
     * Exercise 6: Retrieves the list of posts of users followed by user with id userId from at most two weeks ago, in a
     * certain order. The default order is ascending.
     *
     * @param userId follower user
     * @param order  String specifying order
     * @return ResponseEntity with a {@link PostsByFollowerDTO} containing posts list.
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsByFollowerDTO> followedPosts(@PathVariable Integer userId,
                                                            @RequestParam(defaultValue = "") String order) {
        return new ResponseEntity<>(this.productService.getFollowedPostsByUserId(userId, order), HttpStatus.OK);
    }


}
