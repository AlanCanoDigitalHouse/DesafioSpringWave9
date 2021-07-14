package com.example.desafiospring.controllers;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.DTOS.requests.OnlyUserIDRequestDTO;
import com.example.desafiospring.DTOS.requests.UserIDAndOrderRequestDTO;
import com.example.desafiospring.DTOS.responses.FollowedProductListResponseDTO;
import com.example.desafiospring.DTOS.responses.NewPostResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoProductCountResponseDTO;
import com.example.desafiospring.DTOS.responses.PromoProductListResponseDTO;
import com.example.desafiospring.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedProductListResponseDTO> followedProductList(@Valid UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        return new ResponseEntity<>(productService.followedProductList(userIDAndOrderRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<NewPostResponseDTO> addNewPromoPost(@Valid @RequestBody NewPostRequestDTO newPostRequestDTO) {
        return new ResponseEntity<>(productService.addNewPromoPost(newPostRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{userId}/countPromo")
    public ResponseEntity<PromoProductCountResponseDTO> countPromoProducts(@Valid OnlyUserIDRequestDTO onlyUserIDRequestDTO) {
        return new ResponseEntity<>(productService.countPromoProducts(onlyUserIDRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<PromoProductListResponseDTO> promoProductList(@Valid UserIDAndOrderRequestDTO userIDAndOrderRequestDTO) {
        return new ResponseEntity<>(productService.promoProductList(userIDAndOrderRequestDTO), HttpStatus.OK);
    }
}
