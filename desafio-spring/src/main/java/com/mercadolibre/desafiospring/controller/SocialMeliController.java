package com.mercadolibre.desafiospring.controller;

import com.mercadolibre.desafiospring.dto.request.*;
import com.mercadolibre.desafiospring.dto.response.*;
import com.mercadolibre.desafiospring.exception.*;
import com.mercadolibre.desafiospring.exception.post.*;
import com.mercadolibre.desafiospring.exception.user.*;
import com.mercadolibre.desafiospring.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocialMeliController {
    private SocialMeliService socialMeliService;

    public SocialMeliController(SocialMeliService socialMeliService) {
        this.socialMeliService = socialMeliService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/users/client")
    public ResponseEntity<UserDTOResponse> createClient(
            @RequestBody UserDTO client) throws Exception {
        return new ResponseEntity<>(
                this.socialMeliService.createClient(client), HttpStatus.OK);
    }

    @PostMapping("/users/seller")
    public ResponseEntity<UserDTOResponse> createSeller(
            @RequestBody UserDTO seller) throws Exception {
        return new ResponseEntity<>(
                this.socialMeliService.createSeller(seller), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public HttpStatus makeUserFollowSeller(
            @PathVariable int userId, @PathVariable int userIdToFollow)
            throws Exception {
        this.socialMeliService.makeUserFollowSeller(userId, userIdToFollow);
        return HttpStatus.OK;
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public HttpStatus makeUserUnFollowSeller(
            @PathVariable int userId, @PathVariable int userIdToUnfollow)
            throws Exception {
        this.socialMeliService.makeUserUnfollowSeller(userId, userIdToUnfollow);
        return HttpStatus.OK;
    }

    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity<FollowersCountResponse> getNumberOfFollowersOf(
            @PathVariable int userId) throws SellerDoesNotExistsException {
        return new ResponseEntity<>(
                this.socialMeliService.getNumberOfFollowersOf(userId),
                HttpStatus.OK);
    }

    @PostMapping("/category/{idCategory}")
    public HttpStatus createCategory(@PathVariable int idCategory)
            throws InvalidPostException, InvalidCategoryException {
        this.socialMeliService.createProductsCategory(idCategory);
        return HttpStatus.OK;
    }

    @PostMapping("/products/newpost")
    public HttpStatus createPost(@RequestBody PostDTO post)
            throws Exception {
        this.socialMeliService.createPost(post);
        return HttpStatus.OK;
    }

    @GetMapping("/users/{UserID}/followers/list")
    public ResponseEntity<FollowerListResponse> getListOfFollowersOf(
            @PathVariable int UserID, @RequestParam(required = false) String order)
            throws SellerDoesNotExistsException, InvalidSortCriteriaException {
        return new ResponseEntity<>(
                this.socialMeliService.getListOfFollowersOf(UserID, order),
                HttpStatus.OK);
    }

    @GetMapping("/users/{UserID}/followed/list")
    public ResponseEntity<FollowingListResponse> getListOfFollowedBy(
            @PathVariable int UserID, @RequestParam(required = false) String order)
            throws UserDoesNotExistsException, InvalidSortCriteriaException {
        return new ResponseEntity<>(
                this.socialMeliService.getListOfFollowedBy(UserID, order),
                HttpStatus.OK);
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<SellerLastPostsResponse> getFollowedLastsPosts
            (@PathVariable int userId, @RequestParam(required = false) String order)
            throws UserDoesNotExistsException, InvalidSortCriteriaException {
        return new ResponseEntity<>(
                this.socialMeliService.getFollowedLastsPosts(userId, order),
                HttpStatus.OK);
    }

    @PostMapping("/products/newpromopost")
    public HttpStatus createPromoPost(@RequestBody PromoPostDTO promoPost)
            throws Exception {
        this.socialMeliService.createPromoPost(promoPost);
        return HttpStatus.OK;
    }

    @GetMapping("/products/{userId}/countPromo")
    public ResponseEntity<PromoPostCountResponse> getPromoPostNumberOf(
            @PathVariable int userId) throws SellerDoesNotExistsException {
        return new ResponseEntity<>(
                this.socialMeliService.getPromoPostNumberOf(userId),
                HttpStatus.OK );
    }

    @GetMapping("/products/{userId}/list")
    public ResponseEntity<PromoPostListResponse> getPromoPostListOf(
            @PathVariable int userId, @RequestParam(required = false) String order)
            throws SellerDoesNotExistsException, InvalidSortCriteriaException {
        return new ResponseEntity<>(
                this.socialMeliService.getPromoPostListOf(userId, order),
                HttpStatus.OK );
    }
}
