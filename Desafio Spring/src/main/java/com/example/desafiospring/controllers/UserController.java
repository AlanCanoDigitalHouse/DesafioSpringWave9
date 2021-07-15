package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.request.NewPostDto;
import com.example.desafiospring.dtos.response.FollowersCountDto;
import com.example.desafiospring.dtos.response.FollowersListDto;
import com.example.desafiospring.dtos.response.SellersFollowedListDto;
import com.example.desafiospring.dtos.response.UserSeller2WeeksListDto;
import com.example.desafiospring.services.IUserServices;
import com.example.desafiospring.services.UserServices;
import com.example.desafiospring.dtos.createData.Sellers;
import com.example.desafiospring.dtos.createData.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController

public class UserController {
    private final IUserServices iUserServices;

    public UserController(UserServices userServices) {
        this.iUserServices = userServices;
    }

    @PostMapping("/create-user/{name}")
    public List<Users> createUsers(@PathVariable String name){
       return iUserServices.createUsers(name);
    }
    @PostMapping("/create-seller/{name}")
    public List<Sellers> createSellers(@PathVariable String name){
        return iUserServices.createSellers(name);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<HttpStatus> follow(@PathVariable int userId , @PathVariable int userIdToFollow){
        return iUserServices.follow(userId,userIdToFollow);
    }

    @GetMapping("/users/{userId}/followers/count/")
    public FollowersCountDto countFollowers(@PathVariable int userId){
        return  iUserServices.usersSellersCountDto(userId);
    }

    @GetMapping("/users/{UserID}/followers/list")
    public FollowersListDto listFollowers(@PathVariable int UserID,@RequestParam(defaultValue = "name_asc") String order){
            return iUserServices.followersList(UserID,order);
    }

    @GetMapping("/users/{UserID}/followed/list")
    public SellersFollowedListDto listFollowed(@PathVariable int UserID,@RequestParam(defaultValue = "name_asc") String order){
        return iUserServices.followedList(UserID,order);
    }

    @PostMapping("/products/newpost")
    public ResponseEntity<HttpStatus> newPost(@RequestBody NewPostDto newPost){
        return iUserServices.createPost(newPost);
    }

    @GetMapping("/products/followed/{userId}/list")
    public UserSeller2WeeksListDto userSeller2WeeksList(@PathVariable int userId, @RequestParam (defaultValue = "date_desc") String order ) throws ParseException {
        return iUserServices.postList(userId,order);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<HttpStatus> unFollow(@PathVariable int userId, @PathVariable int userIdToUnfollow){
        return iUserServices.unFollow(userId,userIdToUnfollow);
    }

}
