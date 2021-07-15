package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.request.NewPostRequest;
import com.mercadolibre.socialmeli.dto.response.UserIdPostResponse;
import com.mercadolibre.socialmeli.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final IPostService postService;

    @Autowired
    public ProductController(IPostService postService) {
        this.postService = postService;
    }

    //TODO: US0005
    @PostMapping(value = "/newpost")
    public ResponseEntity createPost(@Valid @RequestBody NewPostRequest post) {
        System.out.println(post);
        return ResponseEntity.ok(postService.createPost(post));
    }
    //TODO:US_0006
    @GetMapping(value = "/followed/{userId}/list")
    public ResponseEntity<UserIdPostResponse>listProductsFollowed(@PathVariable Integer userId, @RequestParam(required = false) String order) throws ParseException {
        return ResponseEntity.ok(postService.listProductsFollowed(userId, order));
    }
}
