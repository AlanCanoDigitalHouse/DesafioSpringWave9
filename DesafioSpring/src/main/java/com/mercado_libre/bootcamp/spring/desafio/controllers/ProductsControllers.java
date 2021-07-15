package com.mercado_libre.bootcamp.spring.desafio.controllers;

import com.mercado_libre.bootcamp.spring.desafio.dtos.request.NewProductRequestDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.request.NewPromoRequestDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowedProductResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.PromoCountResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.PromoListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.services.post.PostServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsControllers {

    private final PostServiceImpl postService;

    private final UserServiceImpl userService;

    @PostMapping("/newpost")
    public ResponseEntity<HttpStatus> newPost(@Valid @RequestBody NewProductRequestDTO newProductRequest) {
        return new ResponseEntity<>(postService.addNewProduct(newProductRequest));
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedProductResponseDTO> getFollowedProducts(@PathVariable int userId, @RequestParam(required = false, defaultValue = "date_desc") String order) {
        return new ResponseEntity<>(userService.getFollowedProducts(userId, order), HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<HttpStatus> newPromoPost(@Valid @RequestBody NewPromoRequestDTO newPromoRequest) {
        return new ResponseEntity<>(postService.addNewPromoProduct(newPromoRequest));
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<PromoCountResponseDTO> getPromosCount(@PathVariable int userId) {
        return new ResponseEntity<>(postService.getPromosCount(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<PromoListResponseDTO> getPromoList(@PathVariable int userId) {
        return new ResponseEntity<>(postService.getPromoList(userId), HttpStatus.OK);
    }

}
