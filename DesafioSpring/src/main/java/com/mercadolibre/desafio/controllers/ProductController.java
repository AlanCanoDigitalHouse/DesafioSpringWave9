package com.mercadolibre.desafio.controllers;

import com.mercadolibre.desafio.dtos.requests.RequestPostDiscountDto;
import com.mercadolibre.desafio.dtos.requests.RequestPostDto;
import com.mercadolibre.desafio.dtos.responses.ResponseCountPromo;
import com.mercadolibre.desafio.dtos.responses.ResponseListPost;
import com.mercadolibre.desafio.dtos.responses.ResponseListPromoPost;
import com.mercadolibre.desafio.exception.PostException;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductServices productServices;

    /**
     * Endpoint to create a new post
     *
     * @param requestPostDto the body of the post
     * @return a response entity
     * @throws Exception
     */
    @PostMapping("/newpost")
    public ResponseEntity<?> addPost(@Valid @RequestBody RequestPostDto requestPostDto) throws Exception {
        productServices.addPostProduct(requestPostDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to get the posts made the users followed by a user
     *
     * @param userId the user id
     * @param order  the order parameter(date_asc or date_desc)
     * @return a response entity
     * @throws Exception
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ResponseListPost> getListPost(@PathVariable Integer userId, @RequestParam(required = false) String order) throws Exception {
        return new ResponseEntity<>(productServices.getPostsFollowed(userId, order), HttpStatus.OK);
    }

    /**
     * Endpoint to add a new promo post
     *
     * @param requestPostDiscountDto the body of the promo post
     * @return a response entity
     * @throws Exception
     */
    @PostMapping("/newpromopost")
    public ResponseEntity<?> addPostDiscount(@Valid @RequestBody RequestPostDiscountDto requestPostDiscountDto) throws Exception {
        productServices.addPostDiscountProduct(requestPostDiscountDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to get the count of the promo post of a user
     *
     * @param userId the user id
     * @return a response entity
     * @throws UserException
     * @throws PostException
     */
    @GetMapping("/{userId}/countPromo/")
    public ResponseEntity<ResponseCountPromo> getPostPromoCount(@PathVariable Integer userId) throws UserException, PostException {
        return new ResponseEntity<>(productServices.getCountPromo(userId), HttpStatus.OK);
    }

    /**
     * Endpoint to get the promo posts of a user
     *
     * @param userId the user id
     * @return a response entity
     * @throws PostException
     * @throws UserException
     */
    @GetMapping("/{userId}/list/")
    public ResponseEntity<ResponseListPromoPost> getPostPromoList(@PathVariable Integer userId) throws PostException, UserException {
        return new ResponseEntity<>(productServices.getPromoPosts(userId), HttpStatus.OK);
    }
}
