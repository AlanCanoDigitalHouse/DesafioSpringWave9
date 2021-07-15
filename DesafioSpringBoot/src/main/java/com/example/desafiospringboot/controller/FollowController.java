package com.example.desafiospringboot.controller;

import com.example.desafiospringboot.dto.FollowCount;
import com.example.desafiospringboot.dto.FollowersDetail;
import com.example.desafiospringboot.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {
    @Autowired 
    private FollowService followService;

    @GetMapping("/users/{userId}/follow/{userIdToFollow}")
    public void followUser (@PathVariable(value = "userId") int userId,
        @PathVariable(value = "userIdToFollow") int userdIdToFollow ){
        followService.seguir(userId, userdIdToFollow);
    }

    @GetMapping("/users/{userId}/followers/count/")
    public FollowCount followCount(@PathVariable(value = "userId") int userId){
        return followService.getFollowersCount(userId);
    }

    @GetMapping("/users/{userId}/followers/list")
    public FollowersDetail followersDetail(@PathVariable(value = "userId") int userId, @RequestParam String order){
        if(order!=null){
            System.out.println("order::: "+order);
            if(order.equals("name_asc")){
                return followService.getFollowersDetailOrdered(userId, true);
            }else if (order.equals("name_desc")){
                return followService.getFollowersDetailOrdered(userId, false);
            }
        }else{
            System.out.println("null order");
        }
        return followService.getFollowersDetail(userId);
    }

    @GetMapping("/users/{userId}/followed/list")
    public FollowersDetail followedDetail(@PathVariable(value = "userId") int userId, @RequestParam String order){
        if(order!=null){
            System.out.println("order::: "+order);
            if(order.equals("name_asc")){
                return followService.getFollowedDetailOrdered(userId, true);
            }else if (order.equals("name_desc")){
                return followService.getFollowedDetailOrdered(userId, false);
            }
        }else{
            System.out.println("null order");
        }
        return followService.getFollowedDetail(userId);
    }
    @GetMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollow (@PathVariable(value = "userId") int userId, @PathVariable(value = "userIdToUnfollow") int userIdToUnfollow){
        followService.unfollow(userId, userIdToUnfollow);
    }
}
