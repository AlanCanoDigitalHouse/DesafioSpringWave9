package com.mercadolibre.social_meli.controller;

import com.mercadolibre.social_meli.dto.response.FollowedResponseDTO;
import com.mercadolibre.social_meli.dto.response.FollowerCountResponseDTO;
import com.mercadolibre.social_meli.dto.response.FollowersResponseDTO;
import com.mercadolibre.social_meli.dto.response.UserResponseDTO;
import com.mercadolibre.social_meli.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        var users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        this.userService.followUser(userId, userIdToFollow);
        return new ResponseEntity<>("Success on Request", HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<String> unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        this.userService.unfollowUser(userId, userIdToUnfollow);
        return new ResponseEntity<>("Success on Request", HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowerCountResponseDTO> getFollowerCount(@PathVariable Integer userId) {
        var responseBody = this.userService.getFollowerCount(userId);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<FollowersResponseDTO> getFollowers(@PathVariable(name = "UserID") Integer userId,
                                                             @RequestParam(required = false) String order) {
        var responseBody = this.userService.getFollowers(userId, order);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/{UserID}/followed/list")
    public ResponseEntity<FollowedResponseDTO> getFollowed(@PathVariable(name = "UserID") Integer userId,
                                                           @RequestParam(required = false) String order) {
        var responseBody = this.userService.getFollowed(userId, order);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
