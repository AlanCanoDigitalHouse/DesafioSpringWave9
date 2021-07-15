package com.example.DesafioSpring.controllers;

import com.example.DesafioSpring.dtos.PublicationDTO;
import com.example.DesafioSpring.dtos.PublicationResponseDTO;
import com.example.DesafioSpring.dtos.PublicationResponseMsgDTO;
import com.example.DesafioSpring.servicies.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 5
    @PostMapping("/newpost")
    public ResponseEntity<PublicationResponseMsgDTO> newPost(@Valid @RequestBody PublicationDTO publication){
        return new ResponseEntity<>(productService.newPost(publication), HttpStatus.OK);
    }

    // 6 // 9
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<List<PublicationResponseDTO>> publicationList(@Valid @PathVariable Integer userId,
                                                                        @Valid @RequestParam(required = false) String order){
        return new ResponseEntity<>(productService.publicationList(userId, order), HttpStatus.OK);
    }

    // Initializer
    @PostMapping("/start-products")
    public ResponseEntity<String> start(){
        return new ResponseEntity<>(productService.start(), HttpStatus.OK );
    }

}
