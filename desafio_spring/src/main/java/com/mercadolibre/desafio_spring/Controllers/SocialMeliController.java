package com.mercadolibre.desafio_spring.Controllers;

import com.mercadolibre.desafio_spring.Router.Router;
import com.mercadolibre.desafio_spring.Services.ISocialMeliService;
import com.mercadolibre.desafio_spring.dtos.request.NewPostRequest;
import com.mercadolibre.desafio_spring.dtos.request.PromoPostRequest;
import com.mercadolibre.desafio_spring.dtos.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
public class SocialMeliController {

    @Autowired
    ISocialMeliService socialMeliService;

    //001
    @PostMapping(value = Router.FOLLOW)
    public HttpStatus follow(@Valid @PathVariable @Min(0) Integer userId,@Valid @PathVariable @Min(0) Integer userIdToFollow){
        return socialMeliService.follow(userId, userIdToFollow);
    }

    //002
    @GetMapping(value = Router.GET_FOLLOWERS_COUNT)
    public FollowersCountResponse followersCount(@Valid @PathVariable @Min(0) Integer userID){
        return socialMeliService.followersCount(userID);
    }

    //003
    @GetMapping(value = Router.GET_FOLLOWERS_LIST)
    public FollowersListResponse getFollowersList(@Valid @PathVariable @Min(0) Integer UserID){
        return socialMeliService.followersList(UserID);
    }

    //004
    @GetMapping(value = Router.GET_FOLLOWED_LIST)
    public FollowedListResponse getFollowedList(@Valid @PathVariable @Min(0) Integer UserID){
        return socialMeliService.followedList(UserID);
    }

    //005
    @PostMapping(value = Router.NEW_POST)
    public HttpStatus newPost(@RequestBody NewPostRequest newPostRequest){
        return socialMeliService.newPost(newPostRequest);
    }

    //006
    @GetMapping(value = Router.PRODUCTS_FOLLOWED)
    public ListPostByUserResponse getPostByUserId(@Valid @PathVariable(value = "userId") @Min(0) Integer userId){
        return socialMeliService.listPostUser(userId);
    }

    //007
    @PostMapping(value = Router.UNFOLLOW)
    public void unfollow(@Valid @PathVariable @Min(0) Integer userId,@Valid @PathVariable @Min(0) Integer userIdToUnfollow){
        socialMeliService.unfollow(userId,userIdToUnfollow);
    }

    //008
    @GetMapping(value = Router.SORTED_FOLLOWERS_BY_ALF, params = {"order"})
    public void sortedFollowersByFollowersName(@Valid @PathVariable @Min(0) Integer UserID,@Valid @RequestParam @NotBlank String order){
        socialMeliService.sortedFollowersUser(UserID,order);
    }

    //008
    @GetMapping(value = Router.SORTED_FOLLOWED_BY_ALF, params = {"order"})
    public void sortedFollowedByFollowersName(@Valid @PathVariable @Min(0) Integer UserID,@Valid @RequestParam @NotBlank String order){
        socialMeliService.sortedFollowedUser(UserID,order);
    }

    //009
    @GetMapping(value = Router.SORTED_PRODUCTS_BY_DATE, params = {"order"})
    public void sortedPostByDate(@Valid @PathVariable @Min(0) Integer userId,@Valid @RequestParam @NotBlank String order){
        socialMeliService.sortedPostUser(userId,order);
    }

    //010
    @PostMapping(value = Router.NEW_PROMO_POST)
    public HttpStatus newPromoPost(@Valid @RequestBody PromoPostRequest promoPostRequest){
        return socialMeliService.newPromoPost(promoPostRequest);
    }

    //011
    @GetMapping(value = Router.COUNT_PROMOS)
    public PromoProductsCountResponse getPromoProductsCount(@Valid @PathVariable @Min(0) Integer userId){
        return socialMeliService.getPromoProductsCount(userId);
    }

    //012
    @GetMapping(value = Router.LISTS_PROMOS_POST)
    public ListPromoProductsResponse getPromoProductsList(@Valid @PathVariable @Min(0) Integer userId){
        return socialMeliService.getPromoProductsList(userId);
    }
}
