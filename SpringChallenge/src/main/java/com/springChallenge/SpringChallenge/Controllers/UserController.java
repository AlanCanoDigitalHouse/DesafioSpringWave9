package com.springChallenge.SpringChallenge.Controllers;

import com.springChallenge.SpringChallenge.Dtos.FollowersResponse;
import com.springChallenge.SpringChallenge.Dtos.Seller;
import com.springChallenge.SpringChallenge.Dtos.Shopper;
import com.springChallenge.SpringChallenge.Services.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping("{userId}/follow/{userIdToFollow}")
    public void follow(@PathVariable int userId, @PathVariable int userIdToFollow) {
         service.follow(userId, userIdToFollow);
    }

    @GetMapping("{userId}/followers/count")
    public FollowersResponse followersCount(@PathVariable int userId){
        return service.followers(userId);
    }

    @GetMapping("{userId}/followers/list")
    public Seller followersList(@PathVariable int userId, @RequestParam(value = "order", required=false) String order){
        return service.getSellerById(userId, order);
    }

    @GetMapping("{userId}/followed/list")
    public Shopper followedList(@PathVariable int userId, @RequestParam(value = "order", required=false) String order){
        return service.getSellersByShopper(userId, order);
    }

    @GetMapping("{userId}/unfollow/{userIdToUnfollow}")
    public void unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow){
        service.unfollow(userId, userIdToUnfollow);
    }

}
