package com.example.desafio1.controllers;

import com.example.desafio1.dto.User;
import com.example.desafio1.exceptions.UserNotFoundException;
import com.example.desafio1.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/{userID}/follow/{userIDToFollow}")
    @ResponseStatus(value = HttpStatus.OK)
    public void follow(@PathVariable Integer userID, @PathVariable Integer userIDToFollow){
        try{
            userService.follow(userID, userIDToFollow);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad bad");
        }
    }

    @GetMapping("/{userID}/followers/count")
    public Integer calculateFollowersCount(@PathVariable Integer userID){
        return userService.calculateNumberOfFollowers(userID);
    }

    @GetMapping("/{userID}/followers/list")
    public List<User> listFollowers(@PathVariable Integer userID){
        return userService.findFollowers(userID);
    }
}
