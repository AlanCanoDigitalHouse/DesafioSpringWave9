package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.request.PostRequestDto;
import com.example.desafiospring.dtos.response.FollowedPostDto;
import com.example.desafiospring.services.ProductServices;
import com.example.desafiospring.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private ProductServices productServices;
    private UserServices userServices;

    public ProductController(ProductServices productServices,UserServices userServices) {
        this.productServices = productServices;
        this.userServices = userServices;
    }

    @PostMapping(path = "/newpost")
    public ResponseEntity<HttpStatus> addNewProduct(@Valid @RequestBody PostRequestDto postRequestDto){
      productServices.createNewPost(postRequestDto);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/followed/{userId}/list")
    public ResponseEntity<FollowedPostDto> getPostFromFollowed(@PathVariable Integer userId){
        var user = userServices.getClientById(userId);
        var listOfFollowed =  user.getFollowed();
        return new ResponseEntity<>(new FollowedPostDto(userId,productServices.getPosts(listOfFollowed)),
                HttpStatus.OK);
    }

}
