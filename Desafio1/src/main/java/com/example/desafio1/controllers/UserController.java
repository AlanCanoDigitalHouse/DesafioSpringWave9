package com.example.desafio1.controllers;

import com.example.desafio1.dto.User;
import com.example.desafio1.dto.response.FollowerCountResponseDTO;
import com.example.desafio1.dto.response.ListUsersResponseDTO;
import com.example.desafio1.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/{userID}/follow/{userIDToFollow}")
    public ResponseEntity<?> follow(@PathVariable  Integer userID, @PathVariable Integer userIDToFollow){
            userService.follow(userID, userIDToFollow);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{userID}/unfollow/{userIDToFollow}")
    public ResponseEntity<?> unfollow(@PathVariable Integer userID, @PathVariable Integer userIDToFollow){
        userService.unfollow(userID, userIDToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userID}/followers/count")
    public FollowerCountResponseDTO calculateFollowersCount(@PathVariable Integer userID){
        User user = userService.findUserById(userID);
        return new FollowerCountResponseDTO(user.getUserId(),
                user.getUserName(),
                userService.calculateNumberOfFollowers(userID));
    }

    @GetMapping("/{userID}/followers/list")
    public ListUsersResponseDTO listFollowers(@PathVariable Integer userID,
                                              @RequestParam(required = false) String order){
        User user = userService.findUserById(userID);
        return new ListUsersResponseDTO(
                user.getUserId(),
                user.getUserName(),
                userService.findFollowers(userID, order)
        );

    }

    @GetMapping("/{userID}/followed/list")
    public ListUsersResponseDTO listFollowed(@PathVariable Integer  userID,
                                   @RequestParam(required = false) String order){
        User user = userService.findUserById(userID);
        return new ListUsersResponseDTO(
                user.getUserId(),
                user.getUserName(),
                userService.findFollowed(userID, order)
        );
    }
}
