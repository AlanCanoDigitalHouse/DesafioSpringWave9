package com.api.firstspringchallenge.controllers;

import com.api.firstspringchallenge.dtos.request.FollowersListRequestDTO;
import com.api.firstspringchallenge.dtos.response.FollowedResponseDTO;
import com.api.firstspringchallenge.dtos.response.FollowersCountResponseDTO;
import com.api.firstspringchallenge.dtos.response.FollowersResponseDTO;
import com.api.firstspringchallenge.services.user.implementation.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UsersController {

    private final UserService service;

    @GetMapping("/users")
    public ResponseEntity<FollowersCountResponseDTO> users() {
        return service.getUsers();
    }
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> followSeller(@PathVariable("userId") int userId, @PathVariable("userIdToFollow") int userIdToFollow) {
        return service.follow(userId, userIdToFollow);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDTO> followersQuantity(@PathVariable("userId") int userId) {
        return service.getFollowersQuantity(userId);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<FollowersResponseDTO> followers(@Valid FollowersListRequestDTO request) {
        return service.getFollowers(request.getUserId(), request.getOrder());
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<FollowedResponseDTO> followed(@PathVariable("userId") int userId) {
        return service.getFollowed(userId);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToFollow}")
    public ResponseEntity<Void> unfollowSeller(@PathVariable("userId") int userId, @PathVariable("userIdToFollow") int userIdToFollow) {
        return service.unfollow(userId, userIdToFollow);
    }


}
