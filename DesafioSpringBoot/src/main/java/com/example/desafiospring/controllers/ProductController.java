package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.request.PostRequestDto;
import com.example.desafiospring.dtos.response.FollowedPostDto;
import com.example.desafiospring.dtos.response.ProductResponseDto;
import com.example.desafiospring.services.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @PostMapping(path = "/newpost")
    public ResponseEntity<HttpStatus> addNewProduct(@Valid @RequestBody PostRequestDto postRequestDto){
      productServices.createNewPost(postRequestDto);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/followed/{userId}/list")
    public ResponseEntity<FollowedPostDto> getPostFromFollowed(@PathVariable Integer userId){
        return new ResponseEntity<>(new FollowedPostDto(userId,productServices.getPostFromUser(userId)),HttpStatus.OK);
    }
}
