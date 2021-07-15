package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("newpost")
    public HttpStatus createPost(@RequestBody @Valid PostDTO postDTO) {
        productService.createPost(postDTO);
        return HttpStatus.OK;
    }

    @GetMapping("followed/{userId}/list")
    public ResponseEntity<?> findPostFromFollowedUsers(@PathVariable Integer userId,
                                                       @RequestParam(defaultValue = "date_desc", required = false) String order) {
        return new ResponseEntity<>(productService.findPostFromFollowedUsers(userId, order), HttpStatus.OK);
    }

}
