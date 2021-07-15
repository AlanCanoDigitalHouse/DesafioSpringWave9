package com.example.desafiospring.controllers;


import com.example.desafiospring.dtos.User;
import com.example.desafiospring.exceptions.AlreadyFollow;
import com.example.desafiospring.exceptions.SameUserException;
import com.example.desafiospring.exceptions.UserDontHaveFollowersorFollowed;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
@Validated
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> follow(
            @Valid @PathVariable
                @Min(message = "need positive number, min required 0", value=0) Integer userId,
            @PathVariable
                @Min(message = "need positive number, min required 0", value=0) Integer userIdToFollow)
            throws UserNotExistException, SameUserException, AlreadyFollow {
            userService.follow(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count")
    public User followersCount(
            @Valid @PathVariable
                @Min(message = "need positive number, min required 0", value=0) Integer userId)
            throws UserNotExistException, UserDontHaveFollowersorFollowed {
        return userService.followersCount(userId);
    }

    @GetMapping("/{userId}/followers/list")
    public User followersList(
            @Valid @PathVariable
                @Min(message = "need positive number, min required 0", value=0) Integer userId,
                              @RequestParam(value = "order", defaultValue = "none", required = false) String order)
            throws UserNotExistException, UserDontHaveFollowersorFollowed {
        return userService.followersList(userId, order);
    }

    @GetMapping("/{userId}/followed/list")
    public User followedList(
            @Valid @PathVariable
            @Min(message = "need positive number, min required 0", value=0) Integer userId,
                             @RequestParam(value = "order", defaultValue = "none", required = false) String order)
            throws UserNotExistException, UserDontHaveFollowersorFollowed {
        return userService.followedList(userId, order);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> unFollow(
            @Valid @PathVariable @Min(message = "need positive number, min required 0", value=0) Integer userId,
            @PathVariable @Min(message = "need positive number, min required 0", value=0) Integer userIdToUnfollow)
                throws UserNotExistException, SameUserException {
            userService.unFollow(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }


}
