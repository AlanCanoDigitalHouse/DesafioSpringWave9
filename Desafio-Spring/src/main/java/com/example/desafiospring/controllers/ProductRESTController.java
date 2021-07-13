package com.example.desafiospring.controllers;


import com.example.desafiospring.DTOS.requests.FollowUserRequestDTO;
import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowUserResponseDTO;
import com.example.desafiospring.DTOS.responses.NewPostResponseDTO;
import com.example.desafiospring.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductRESTController {
    ProductService productService;

    public ProductRESTController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<NewPostResponseDTO> addNewPost(@Valid @RequestBody NewPostRequestDTO newPostRequestDTO) {
        return new ResponseEntity<>(productService.addNewPost(newPostRequestDTO), HttpStatus.OK);
    }
}
