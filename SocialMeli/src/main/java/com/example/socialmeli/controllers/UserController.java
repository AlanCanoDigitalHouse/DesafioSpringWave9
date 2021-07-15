package com.example.socialmeli.controllers;

import com.example.socialmeli.dtos.responses.AllFollowedResponseDto;
import com.example.socialmeli.dtos.responses.AllFollowersResponseDto;
import com.example.socialmeli.dtos.responses.AllUserResponseDto;
import com.example.socialmeli.dtos.responses.CountFollowersResponseDto;
import com.example.socialmeli.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        userService.follow(userId,userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Void> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        userService.unfollow(userId,userIdToUnfollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowersResponseDto> getCountFollower(@PathVariable Integer userId){
        userService.countFollowers(userId);
        return new ResponseEntity<>(userService.countFollowers(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/followers/list", params = {"order"})
    public ResponseEntity<AllFollowersResponseDto> getFollowers(@PathVariable Integer userId, @RequestParam String order){
        return new ResponseEntity<>(userService.allFollowers(userId, order), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/followers/list")
    public ResponseEntity<AllFollowersResponseDto> getFollowers(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.allFollowers(userId, null), HttpStatus.OK);
    }

    @GetMapping(value="/{userId}/followed/list", params = {"order"})
    public ResponseEntity<AllFollowedResponseDto> getFollowing(@PathVariable Integer userId, @RequestParam String order){
        return new ResponseEntity<>(userService.allFollowed(userId, order), HttpStatus.OK);
    }

    @GetMapping(value="/{userId}/followed/list")
    public ResponseEntity<AllFollowedResponseDto> getFollowing(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.allFollowed(userId, null), HttpStatus.OK);
    }

    @GetMapping(value="/list")
    public ResponseEntity<AllUserResponseDto> getAllUsers(){
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }
}
