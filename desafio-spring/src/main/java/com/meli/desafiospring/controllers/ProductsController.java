package com.meli.desafiospring.controllers;

import com.meli.desafiospring.DTOs.PostDTO;
import com.meli.desafiospring.DTOs.response.PostsListResponseDTO;
import com.meli.desafiospring.routers.Router;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    // US0005
    @PostMapping(Router.NEW_POST)
    public ResponseEntity<HttpStatus> new_post(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // US0006, US0008, US0009
    @GetMapping(Router.FOLLOWED_POST_LIST)
    public PostsListResponseDTO sellers_followed_post_list_last2weeks(@PathVariable Integer userId) {
        return null;
    }
}
