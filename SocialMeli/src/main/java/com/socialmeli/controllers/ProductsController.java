package com.socialmeli.controllers;

import com.socialmeli.dtos.request.PostRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @PostMapping("/newpost")
    public ResponseEntity addPost(@Valid @RequestBody PostRequestDTO post){
        return new ResponseEntity(HttpStatus.OK);
    }
}
