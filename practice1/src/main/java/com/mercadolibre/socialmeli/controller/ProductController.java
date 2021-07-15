package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.request.PostDTO;
import com.mercadolibre.socialmeli.dto.request.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostPromoCountResponseDTO;
import com.mercadolibre.socialmeli.dto.response.PostPromoListResponse;
import com.mercadolibre.socialmeli.dto.response.PostResponseListDTO;
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

    @PostMapping("newpromopost")
    public HttpStatus createPromoPost(@RequestBody @Valid PromoPostRequestDTO postDTO) {
        productService.createPromoPost(postDTO);
        return HttpStatus.OK;
    }

    @GetMapping("followed/{userId}/list")
    public ResponseEntity<PostResponseListDTO> findPostFromFollowedUsers(@PathVariable Integer userId,
                                                                         @RequestParam(defaultValue = "date_desc",
                                                                                 required = false)
                                                                                 String order) {
        return new ResponseEntity<>(productService.findPostFromFollowedUsers(userId, order), HttpStatus.OK);
    }

    @GetMapping("{userId}/countPromo")
    public ResponseEntity<PostPromoCountResponseDTO> countPromoPostByUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(productService.countPromoPostsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("{userId}/list")
    public ResponseEntity<PostPromoListResponse> getPromoListPostByUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(productService.getPromoPostListByUser(userId), HttpStatus.OK);
    }

}
