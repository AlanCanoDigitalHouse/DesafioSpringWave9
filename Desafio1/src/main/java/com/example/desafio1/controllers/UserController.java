package com.example.desafio1.controllers;

import com.example.desafio1.dto.User;
import com.example.desafio1.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/{userID}/follow/{userIDToFollow}")
    public ResponseEntity follow(@PathVariable @Valid Integer userID, @PathVariable Integer userIDToFollow){

            userService.follow(userID, userIDToFollow);
            return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{userID}/unfollow/{userIDToFollow}")
    public ResponseEntity unfollow(@PathVariable Integer userID, @PathVariable Integer userIDToFollow){

        userService.unfollow(userID, userIDToFollow);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{userID}/followers/count")
    public Integer calculateFollowersCount(@PathVariable Integer userID){
        return userService.calculateNumberOfFollowers(userID);
    }

    @GetMapping("/{userID}/followers/list")
    public List<User> listFollowers(@PathVariable Integer userID){
        return userService.findFollowers(userID);
    }

    @GetMapping("/{userID}/followed/list")
    public List<User> listFollowed(@PathVariable Integer  userID){
        return userService.findFollowed(userID);
    }


}
