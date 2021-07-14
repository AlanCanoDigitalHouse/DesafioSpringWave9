package com.mercadolibre.social_meli.controller;

import com.mercadolibre.social_meli.dto.request.ProductRequestDTO;
import com.mercadolibre.social_meli.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
