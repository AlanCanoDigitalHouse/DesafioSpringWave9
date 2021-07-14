package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dto.Publication;
import com.mercadolibre.socialmeli.dto.respons.PromProductsResponse;
import com.mercadolibre.socialmeli.dto.respons.PromotionCountResponse;
import com.mercadolibre.socialmeli.dto.respons.PublicationsResponse;
import com.mercadolibre.socialmeli.exceptions.PreconditionError;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;
import com.mercadolibre.socialmeli.services.ProductService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Publication>> getAllPublications() {
        return new ResponseEntity<List<Publication>>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/newpost")
    public ResponseEntity<Publication> postPublications(@Valid @RequestBody Publication publication) throws UserBadRequest {
        return new ResponseEntity<Publication>(service.postPublications(publication), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PublicationsResponse> getPublicationByFollowed(@Validated @PathVariable int userId, @RequestParam(required = false) String order) throws ParseException, UserBadRequest {
        return new ResponseEntity<PublicationsResponse>(service.getPublicationByFollowed(userId, order), HttpStatus.OK);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<Publication> postPromotion(@Valid @RequestBody Publication promotion) throws UserBadRequest, PreconditionError {
        return new ResponseEntity<Publication>(service.postPromotion(promotion), HttpStatus.OK);
    }

    @GetMapping("/{userId}/countPromo/")
    public ResponseEntity<PromotionCountResponse> getCountProm(@Valid @PathVariable int userId) throws UserBadRequest {
        return new ResponseEntity<PromotionCountResponse>(service.getCountProm(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/list/")
    public ResponseEntity<PromProductsResponse> getProductsByProm(@Valid @PathVariable int userId, @RequestParam(required = false) String order) throws UserBadRequest {
        return new ResponseEntity<PromProductsResponse>(service.getProductsByProm(userId, order), HttpStatus.OK);
    }

}
