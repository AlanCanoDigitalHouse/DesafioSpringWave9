package com.mercadolibre.socialmeli.controller;

import com.mercadolibre.socialmeli.dto.response.UserWithFollowedDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersCountDTO;
import com.mercadolibre.socialmeli.dto.response.UserWithFollowersDTO;
import com.mercadolibre.socialmeli.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String follow(
            @PathVariable int userId, @PathVariable int userIdToFollow
    ){
        userService.followUser(userId, userIdToFollow);
        return "user successfully followed";
    }

    @GetMapping("/users/{userId}/followers/count/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserWithFollowersCountDTO getUserWithFollowersCount(
            @PathVariable int userId
    ){
        return userService.getUserWithFollowersCountDTO(userId);
    }

    @GetMapping("/users/{UserID}/followers/list")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserWithFollowersDTO getUserWithFollowersDTO(
            @PathVariable int UserID
    ){
        return userService.getUserWithFollowers(UserID);
    }

    @GetMapping("/users/{UserID}/followed/list")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserWithFollowedDTO getUserWithFollowed(
            @PathVariable int UserID
    ){
        return userService.getUSerWithFollowed(UserID);
    }
}
