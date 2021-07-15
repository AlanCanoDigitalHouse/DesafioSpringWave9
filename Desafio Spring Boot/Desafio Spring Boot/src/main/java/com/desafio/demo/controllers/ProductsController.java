package com.desafio.demo.controllers;

import com.desafio.demo.dtos.products.request.ProductRequestDto;
import com.desafio.demo.dtos.products.response.ProductResponseDto;
import com.desafio.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    //dar de alta una nueva publicacion
    @PostMapping("/newpost")
    public ResponseEntity<Void> releasePost(@RequestBody @Valid ProductRequestDto productRequestDto){
        productService.releasePost(productRequestDto);
        return ResponseEntity.ok().build();
    }

    //obtener un listado de las publicaciones realizadas por los vendedores que sigue un usuario
    //en las ultimas dos semanas
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ProductResponseDto> getLastsPosts(@PathVariable int userId, @RequestParam(required = false) String order){
        ProductResponseDto response = productService.getLastsPosts(userId, order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
