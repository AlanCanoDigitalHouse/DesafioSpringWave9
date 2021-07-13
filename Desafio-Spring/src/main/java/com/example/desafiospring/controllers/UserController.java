package com.example.desafiospring.controllers;

import com.example.desafiospring.dtos.request.PublicationRequestDTO;
import com.example.desafiospring.dtos.response.FollowedsListDTO;
import com.example.desafiospring.dtos.response.FollowersCountDTO;
import com.example.desafiospring.dtos.response.FollowersListDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import com.example.desafiospring.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("users")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //001
    @PostMapping(path = "/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> follow(@PathVariable Integer userId,@PathVariable Integer userIdToFollow) throws UserNotFindException {
        return userService.newFollow(userId,userIdToFollow);
    }

    //002
    @GetMapping(path = "/{userId}/followers/count")
    public FollowersCountDTO followersCount(@PathVariable Integer userId) throws UserNotFindException {
        return userService.followerCount(userId);
    }

    //003
    @GetMapping(path = "/{userId}/followers/list")
    public FollowersListDTO followersList(@PathVariable Integer userId) throws UserNotFindException {
        return userService.followerList(userId);
    }

    //004
    @GetMapping(path = "/{userId}/followed/list")
    public FollowedsListDTO followedList(@PathVariable Integer userId) throws UserNotFindException {
        return userService.followedList(userId);
    }


}
