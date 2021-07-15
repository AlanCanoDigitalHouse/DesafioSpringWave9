package com.example.demo.controllers;

import com.example.demo.dtos.FollowedListResponseDTO;
import com.example.demo.dtos.FollowerCountResponseDTO;
import com.example.demo.dtos.FollowerListResponseDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void addFollower(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        try {
            userService.followSeller(userId, userIdToFollow);
        } catch (Exception e){
            throw new BadRequestException("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowerCountResponseDTO> followerCount (@PathVariable Integer userId){
        try {
            return userService.getSellerFollowersCount(userId);
        } catch (Exception e) {
            throw new BadRequestException("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowerListResponseDTO> followerList (@PathVariable Integer userId, @RequestParam(required = false) String order){
        try {
            return userService.getFollowersList(userId, order);
        } catch (Exception e) {
            throw new BadRequestException("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowedListResponseDTO> followedList (@PathVariable Integer userId, @RequestParam(required = false) String order){
        try {
            return userService.getFollowedList(userId, order);
        } catch (Exception e) {
            throw new BadRequestException("Error: " + e.getMessage());
        }
    }

    @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
    public void removeFollower(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        try {
            userService.unfollowUser(userId, userIdToUnfollow);
        } catch (Exception e) {
            throw new BadRequestException("Error: " + e.getMessage());
        }
    }
}