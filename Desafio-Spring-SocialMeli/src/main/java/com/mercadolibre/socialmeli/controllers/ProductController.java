package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.posts.requests.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PostListResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.requests.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PostResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PromoPostListResponseDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.PromoPostQtyResponseDTO;
import com.mercadolibre.socialmeli.exceptions.users.UserNotFoundException;
import com.mercadolibre.socialmeli.services.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private final IPostService iPostService;

    public ProductController(@Qualifier("productServ") IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @PostMapping(path = "/newpost")
    public ResponseEntity<PostResponseDTO> newPost(@Valid @RequestBody PostRequestDTO post) throws UserNotFoundException {
        return new ResponseEntity<>(this.iPostService.createPost(post), HttpStatus.OK);
    }

    @GetMapping(path = "/followed/{userId}/list")
    public ResponseEntity<PostListResponseDTO> postsListing(@Valid @NotNull @Min(message = "You must enter an Id greater than 0", value = 1) @PathVariable Integer userId,
                                                            @RequestParam(name = "order", required = false, defaultValue = "date_desc") String order) throws UserNotFoundException {
        return new ResponseEntity<>(this.iPostService.postsListOf(userId,order), HttpStatus.OK);
    }

    @PostMapping(path = "/newpromopost")
    public ResponseEntity<PostResponseDTO> newPromoPost(@Valid @RequestBody PromoPostRequestDTO promoPost) throws UserNotFoundException {
        return new ResponseEntity<>(this.iPostService.createPromoPost(promoPost), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/countPromo/")
    public ResponseEntity<PromoPostQtyResponseDTO> countPromoPosts(@Valid @NotNull @Min(message = "You must enter an Id greater than 0", value = 1) @PathVariable Integer userId) throws UserNotFoundException {
        return new ResponseEntity<>(this.iPostService.countPromoPostsOf(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/list/")
    public ResponseEntity<PromoPostListResponseDTO> promoPostsListing(@Valid @NotNull @Min(message = "You must enter an Id greater than 0", value = 1) @PathVariable Integer userId) throws UserNotFoundException {
        return new ResponseEntity<>(this.iPostService.promoPostsListOf(userId), HttpStatus.OK);
    }


}
