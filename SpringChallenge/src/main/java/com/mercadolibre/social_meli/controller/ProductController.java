package com.mercadolibre.social_meli.controller;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.dto.request.PromoProductRequestDTO;
import com.mercadolibre.social_meli.dto.response.FollowedPostsResponseDTO;
import com.mercadolibre.social_meli.dto.response.PromoCountResponseDTO;
import com.mercadolibre.social_meli.dto.response.UserPromoPostsResponseDTO;
import com.mercadolibre.social_meli.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<String> postProduct(@Valid @RequestBody ProductRequestDTO productData) {
        this.productService.postNewProduct(productData);

        return new ResponseEntity<>("Product posted successfully", HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<String> postPromoProduct(@Valid @RequestBody PromoProductRequestDTO productData) {
        this.productService.postNewPromoProduct(productData);

        return new ResponseEntity<>("Product posted successfully", HttpStatus.OK);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<PromoCountResponseDTO> getUserPromoCount(@PathVariable Integer userId) {
        var response = this.productService.getUserPromoCount(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedPostsResponseDTO> getRecentPosts(@PathVariable Integer userId,
                                                                   @RequestParam(required = false) String order) {

        var posts = this.productService.getFollowedRecentPosts(userId, order);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<UserPromoPostsResponseDTO> getUserPromoPosts(@PathVariable Integer userId,
                                                                       @RequestParam(required = false) String order) {

        var userPromoPosts = this.productService.getUserPromoPosts(userId, order);

        return new ResponseEntity<>(userPromoPosts, HttpStatus.OK);
    }

}
