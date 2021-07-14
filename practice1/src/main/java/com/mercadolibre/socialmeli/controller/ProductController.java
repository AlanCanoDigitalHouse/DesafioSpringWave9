package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.PostDTO;
import com.mercadolibre.socialmeli.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
