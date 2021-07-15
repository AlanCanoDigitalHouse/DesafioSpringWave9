package com.meli.desafiospring.controllers;

import com.meli.desafiospring.DTOs.request.PromoPostRequestDTO;
import com.meli.desafiospring.DTOs.response.PostResponseDTO;
import com.meli.desafiospring.DTOs.request.PostRequestDTO;
import com.meli.desafiospring.DTOs.response.PostsListResponseDTO;
import com.meli.desafiospring.DTOs.response.PromoPostCountResponseDTO;
import com.meli.desafiospring.exceptions.custom.PostDetailIsNullException;
import com.meli.desafiospring.routers.Router;
import com.meli.desafiospring.services.UserManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    UserManager userManager;

    public ProductsController(UserManager userManager) {
        this.userManager = userManager;
    }

    // US0005
    @PostMapping(Router.NEW_POST)
    public ResponseEntity<PostResponseDTO> newPost(@RequestBody @Valid PostRequestDTO postRequestDTO) {
        if (postRequestDTO.getDetail()==null)
            throw new PostDetailIsNullException("The detail on the post is null !");
        return userManager.newPost(postRequestDTO);
    }



    // US0006, US0008, US0009
    @GetMapping(Router.FOLLOWED_POST_LIST)
    public ResponseEntity<PostsListResponseDTO> sellers_followed_post_list_last2weeks(@PathVariable Integer userId,
                                                                                      @RequestParam(required=false) String order) {
        return userManager.lastFollowedPosts(userId, order);
    }

    // US0010
    @PostMapping(Router.NEW_PROMO_POST)
    public ResponseEntity<PostResponseDTO> newPromoPost(@RequestBody @Valid PromoPostRequestDTO promoPostRequestDTO) {
        if (promoPostRequestDTO.getDetail()==null)
            throw new PostDetailIsNullException("The detail on the post is null !");
        return userManager.newPromoPost(promoPostRequestDTO);
    }

    // US0011
    @GetMapping(Router.COUNT_PROMO_POST)
    public ResponseEntity<PromoPostCountResponseDTO> promoPostCount(@PathVariable Integer userId) {
        return userManager.promoPostCount(userId);
    }

    // US0012
    @GetMapping(Router.PROMO_POSTS_LIST)
    public ResponseEntity<PostsListResponseDTO> promoPostsList(@PathVariable Integer userId) {
        return userManager.promoPostsList(userId);
    }

}
