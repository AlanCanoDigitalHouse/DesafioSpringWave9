package com.mercadolibre.desafio.controllers;

import com.mercadolibre.desafio.dtos.RequestPostDto;
import com.mercadolibre.desafio.dtos.ResponseListPost;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @PostMapping("/newpost")
    public ResponseEntity<?> addPost(@Valid @RequestBody RequestPostDto requestPostDto) throws Exception {
        productServices.addPostProduct(requestPostDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ResponseListPost> getListPost(@PathVariable Integer userId,@RequestParam String order) throws Exception {
        return new ResponseEntity<>(productServices.getPostsFollowed(userId,order),HttpStatus.OK);
    }
}
