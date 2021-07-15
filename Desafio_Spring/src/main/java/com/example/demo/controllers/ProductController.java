package com.example.demo.controllers;

import com.example.demo.dtos.FollowedProductListResponse;
import com.example.demo.dtos.PostDTO;
import com.example.demo.exceptions.PostException;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping ("/newpost")
    public void newPost(@RequestBody PostDTO postDTO) {
        try {
            productService.createPost(postDTO);
        } catch (Exception e) {
            throw new PostException(e.getMessage());
        }
    }

    @GetMapping ("/followed/{userId}/list")
    public ResponseEntity<FollowedProductListResponse> getProductsPublishedByFollowedOnLastTwoWeeks(@PathVariable Integer userId, @RequestParam(defaultValue = "date_desc") String order) {
        try {
            if(order.equals("date_desc") || order.equals("date_asc")) {
                return productService.getProductsPublishedByFollowedOnLastTwoWeeks(userId, order);
            } else {
                throw new PostException("Invalid ordering type provided!!: " + order);
            }
        } catch (Exception e) {
            throw new PostException(e.getMessage());
        }
    }
}
