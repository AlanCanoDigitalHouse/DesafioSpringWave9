package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.responsedtos.SellerWithPostsDTO;
import com.example.desafiospring.exceptions.*;
import com.example.desafiospring.services.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IPostService iPostService;

    public ProductController(IPostService iPostService){
        this.iPostService = iPostService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<String> newPost(@Valid @RequestBody PostDTO post) throws UserNotFoundByIdException, PostNotFoundByIdException, PostAlreadyExistsException, ProductAlreadyExistsException{
        this.iPostService.createNewPost(post);
        return new ResponseEntity<>("TODO OK", HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public SellerWithPostsDTO getPostByFollower(@PathVariable Integer userId, @RequestParam(value="order", required = false) String order) throws UserNotFoundByIdException, OrderNotExistsException {
        return this.iPostService.getPostFromSeller(userId, order);
    }

}
