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

@RestController
public class SocialMeliController {
    @Autowired
    ISocialMeliService socialMeliService;

    @PostMapping(value = Router.FOLLOW)
    public HttpStatus follow(@PathVariable int userId, @PathVariable int userIdToFollow){
        return socialMeliService.follow(userId, userIdToFollow);
    }

    @GetMapping(value = Router.GET_FOLLOWERS_COUNT)
    public FollowersCountResponse followersCount(@PathVariable int userID){
        return socialMeliService.followersCount(userID);
    }


    @GetMapping(value = Router.GET_FOLLOWERS_LIST)
    public FollowersListResponse getFollowersList(@PathVariable int userID){
        return socialMeliService.followersList(userID);
    }

    @GetMapping(value = Router.GET_FOLLOWED_LIST)
    public FollowedListResponse getFollowedList(@PathVariable int UserID){
        return socialMeliService.followedList(UserID);
    }

    @PostMapping(value = Router.NEW_POST)
    public HttpStatus newPost(@Valid @RequestBody NewPostRequest newPostRequest){
        return socialMeliService.newPost(newPostRequest);
    }

    @GetMapping(value = Router.PRODUCTS_FOLLOWED)
    public ListPostByUserResponse getPostByUserId(@PathVariable(value = "userId") int userId){
        return socialMeliService.listPostUser(userId);
    }

    @PostMapping(value = Router.UNFOLLOW)
    public void unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow){
        socialMeliService.unfollow(userId,userIdToUnfollow);
    }

    @GetMapping(value = Router.SORTED_FOLLOWERS_BY_ALF, params = {"order"})
    public void sortedFollowersByFollowersName( @PathVariable int UserID, @RequestParam String order){
        socialMeliService.sortedFollowersUser(UserID,order);
    }

    @GetMapping(value = Router.SORTED_FOLLOWED_BY_ALF, params = {"order"})
    public void sortedFollowedByFollowersName(@PathVariable int UserID, @RequestParam String order){
        socialMeliService.sortedFollowedUser(UserID,order);
    }

    @GetMapping(value = Router.SORTED_PRODUCTS_BY_DATE, params = {"order"})
    public void sortedPostByDate(@PathVariable int userId, @RequestParam String order){
        socialMeliService.sortedPostUser(userId,order);
    }

    @PostMapping(value = Router.NEW_PROMO_POST)
    public HttpStatus newPromoPost(@Valid @RequestBody PromoPostRequest promoPostRequest){
        return socialMeliService.newPromoPost(promoPostRequest);
    }

    @GetMapping(value = Router.COUNT_PROMOS)
    public PromoProductsCountResponse getPromoProductsCount(@PathVariable int userId){
        return socialMeliService.getPromoProductsCount(userId);
    }

    @GetMapping(value = Router.LISTS_PROMOS_POST)
    public ListPromoProductsResponse getPromoProductsList(@PathVariable int userId){
        return socialMeliService.getPromoProductsList(userId);
    }
}
