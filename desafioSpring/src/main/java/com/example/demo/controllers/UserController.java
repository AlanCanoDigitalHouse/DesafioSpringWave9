package com.example.demo.controllers;

import com.example.demo.dtos.response.FollowedList;
import com.example.demo.dtos.response.FollowersCount;
import com.example.demo.dtos.response.FollowersList;
import com.example.demo.exceptions.DuplicateUser;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.exceptions.UserRemoved;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity follow(@PathVariable Integer userId,
                                 @PathVariable Integer userIdToFollow) throws UserNotFound, DuplicateUser {
        userService.addFollowing(userId, userIdToFollow);
        return new ResponseEntity("User " + userId + " is now following to User " + userIdToFollow, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count/")
    public ResponseEntity followersCount(@PathVariable Integer userId) throws UserNotFound, UserRemoved {
        FollowersCount response = userService.followersCount(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity followersList(@PathVariable Integer userId, @RequestParam(value = "order", defaultValue = "") String order) throws UserNotFound, UserRemoved {
        FollowersList response = new FollowersList();
        if(!order.isBlank())
            response = userService.getOrderFollowersList(order, userId);
        else
            response = userService.followersList(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity followedList(@PathVariable Integer userId, @RequestParam(value = "order", defaultValue = "") String order) throws UserNotFound, UserRemoved {
        FollowedList response = new FollowedList();
        if(!order.isBlank())
            response = userService.getOrderFollowedList(order, userId);
        else
            response = userService.followedList(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) throws UserNotFound, UserRemoved, DuplicateUser {
        userService.unfollow(userId, userIdToUnfollow);
        return new ResponseEntity("User " + userId + " unfollow User " + userIdToUnfollow, HttpStatus.OK);

    }
}
